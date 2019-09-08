package com.mgalhardo.fixture.provider

import com.mgalhardo.fixture.Fixture
import com.mgalhardo.fixture.external.MakeRandomInstanceConfig
import com.mgalhardo.fixture.external.NoUsableConstructor
import com.mgalhardo.fixture.external.getKType
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.full.createInstance

class ReflectTypeProvider(
    val fixture: Fixture,
    private val config: MakeRandomInstanceConfig = MakeRandomInstanceConfig()
) {

    inline fun <reified T : Any> nextOf(): T {
        return nextRandomInstance(T::class, getKType<T>()) as T
    }

    fun nextRandomInstance(classRef: KClass<*>, type: KType): Any? {
        val primitive = nextStandardOrNull(classRef, type)
        if (primitive != null) {
            return primitive
        }

        val constructors = classRef.constructors
            .sortedBy { it.parameters.size }

//        try {
        if (constructors.isEmpty()) {
            // If it doesn't have a constructor, try to check if it is an Object Type.
            return classRef.objectInstance ?: classRef.createInstance()
        } else {
            for (constructor in constructors) {
                val arguments = constructor.parameters
                    .map { nextRandomOfParameter(it.type, classRef, type) }
                    .toTypedArray()

                return constructor.call(*arguments)

            }
        }
//        } catch (e: Throwable) {
//            e.printStackTrace()
//        }

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
        return when (val classifier = paramType.classifier) {
            is KClass<*> -> {
                if (classifier.isSealed) {
                    // If is a sealed class, takes the first nested class.
                    val nestedClass = classifier.sealedSubclasses.firstOrNull()
                    if (nestedClass != null) {
                        return fixture.reflectNextOf(nestedClass, paramType)
                    }
                }
                // If it is a normal object, creates it.
                fixture.reflectNextOf(classifier, paramType)
            }
            is KTypeParameter -> {
                val typeParameterName = classifier.name
                val typeParameterId =
                    classRef.typeParameters.indexOfFirst { it.name == typeParameterName }
                val parameterType = type.arguments[typeParameterId].type ?: getKType<Any>()
//                nextRandomInstance(parameterType.classifier as KClass<*>, parameterType)
                fixture.reflectNextOf(parameterType.classifier as KClass<*>, parameterType)
            }
//            is KClass<*> -> nextRandomInstance(classifier, paramType)
//            is KTypeParameter -> {
//                val typeParameterName = classifier.name
//                val typeParameterId =
//                    classRef.typeParameters.indexOfFirst { it.name == typeParameterName }
//                val parameterType = type.arguments[typeParameterId].type ?: getKType<Any>()
//                nextRandomInstance(parameterType.classifier as KClass<*>, parameterType)
//            }
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

//    FIXME: Dead code. Random Char / String
//
//    private fun makeRandomChar(random: Random) = ('A'..'z').random(random)
//    private fun makeRandomString(random: Random) =
//        (1..random.nextInt(config.possibleStringSizes.first, config.possibleStringSizes.last + 1))
//            .map { makeRandomChar(random) }
//            .joinToString(separator = "") { "$it" }

}