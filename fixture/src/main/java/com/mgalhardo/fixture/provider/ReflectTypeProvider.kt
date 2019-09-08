package com.mgalhardo.fixture.provider

import com.mgalhardo.fixture.Fixture
import com.mgalhardo.fixture.external.MakeRandomInstanceConfig
import com.mgalhardo.fixture.external.NoUsableConstructor
import com.mgalhardo.fixture.external.getKType
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.KVisibility
import kotlin.reflect.full.createInstance

class ReflectTypeProvider(
    val fixture: Fixture,
    private val config: MakeRandomInstanceConfig = MakeRandomInstanceConfig()
) {

    inline fun <reified T : Any> nextOf(): T {
        return nextRandomInstance(T::class, getKType<T>()) as T
    }

    fun nextRandomInstance(classRef: KClass<*>, type: KType): Any? {
        // Nullable variables will always returns null.
        if (type.isMarkedNullable) return null

        // Check for a standard instance (e.g., Int or String).
        val primitive = nextStandardOrNull(classRef, type)
        if (primitive != null) {
            return primitive
        }

        // Get the first non-private constructor with the least number of arguments.
        val constructors = classRef.constructors
            .filter { it.visibility != KVisibility.PRIVATE }
            .sortedBy { it.parameters.size }

        // If it doesn't have a constructor, try to check if it is an Object Type.
        if (constructors.isEmpty()) {
            return classRef.objectInstance ?: classRef.createInstance()
        } else {
            for (constructor in constructors) {
                val arguments = constructor.parameters
                    .map { nextRandomOfParameter(it.type, classRef, type) }
                    .toTypedArray()

                return constructor.call(*arguments)

            }
        }

        throw NoUsableConstructor()
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun nextStandardOrNull(classRef: KClass<*>, type: KType) = when (classRef) {
        Int::class -> fixture.nextInt()
        Long::class -> fixture.nextLong()
        Double::class -> fixture.nextDouble()
        Float::class -> fixture.nextFloat()
        Char::class -> fixture.nextString()
        String::class -> fixture.nextString()
        List::class, Collection::class -> nextRandomList(classRef, type)
        Map::class -> nextRandomMap(classRef, type)
        else -> null
    }

    private fun nextRandomOfParameter(
        paramType: KType,
        classRef: KClass<*>,
        type: KType
    ): Any? {
        // Nullable variables will always returns null.
        if (paramType.isMarkedNullable) return null

        return when (val classifier = paramType.classifier) {
            is KClass<*> -> {

                if (classifier.isSealed) {
                    // If is a sealed class, takes the first nested class.
                    val nestedClass = classifier.sealedSubclasses.firstOrNull()
                    if (nestedClass != null) {
                        return fixture.reflectNextOf(nestedClass, paramType)
                    }
                }
                // If is an abstraction, creates a Proxy instance.
//                if (classifier.isAbstract) {
//                    return Proxy.newProxyInstance(
//                        ClassLoader.getSystemClassLoader(),
//                        arrayOf(paramType::class.java)
//                    ) { _: Any, method: Method, _: Array<out Any> ->
//                        method.kotlinFunction
//                            ?.returnType
//                            ?.jvmErasure
//                            ?.createInstance()
//                    }
//                }
                // If it is a normal object, creates it.
                fixture.reflectNextOf(classifier, paramType)
            }
            is KTypeParameter -> {
                val typeParameterName = classifier.name
                val typeParameterId =
                    classRef.typeParameters.indexOfFirst { it.name == typeParameterName }
                val parameterType = type.arguments[typeParameterId].type ?: getKType<Any>()
                fixture.reflectNextOf(parameterType.classifier as KClass<*>, parameterType)
            }
            else -> {
                throw Error("Type of the classifier $classifier is not supported")
            }
        }
    }

    private fun nextRandomList(classRef: KClass<*>, type: KType): List<Any?> {
        val numOfElements = fixture.nextInt(
            config.possibleCollectionSizes.first,
            config.possibleCollectionSizes.last + 1
        )
        val elemType = type.arguments[0].type!!
        return (1..numOfElements)
            .map { nextRandomOfParameter(elemType, classRef, type) }
    }

    private fun nextRandomMap(classRef: KClass<*>, type: KType): Map<Any?, Any?> {
        val numOfElements = fixture.nextInt(
            config.possibleCollectionSizes.first,
            config.possibleCollectionSizes.last + 1
        )
        val keyType = type.arguments[0].type!!
        val valType = type.arguments[1].type!!
        val keys = (1..numOfElements)
            .map { nextRandomOfParameter(keyType, classRef, type) }
        val values = (1..numOfElements)
            .map { nextRandomOfParameter(valType, classRef, type) }
        return keys.zip(values).toMap()
    }
}