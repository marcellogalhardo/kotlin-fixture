package com.mgalhardo.fixture.generator

import com.mgalhardo.fixture.Fixture
import com.mgalhardo.fixture.external.MakeRandomInstanceConfig
import com.mgalhardo.fixture.external.NoUsableConstructor
import com.mgalhardo.fixture.external.getKType
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter

class ReflectGenerator(
    val fixture: Fixture,
    private val config: MakeRandomInstanceConfig = MakeRandomInstanceConfig()
) {

    inline fun <reified T : Any> create(): T {
        return makeRandomInstance(T::class, getKType<T>()) as T
    }

    fun makeRandomInstance(classRef: KClass<*>, type: KType): Any? {
        val primitive = makeStandardInstanceOrNull(classRef, type)
        if (primitive != null) {
            return primitive
        }

        val constructors = classRef.constructors
            .shuffled()
//            .filter { it.isOpen }
//            .sortedBy { it.parameters.size }

        for (constructor in constructors) {
            try {
                val arguments = constructor.parameters
                    .map { makeRandomInstanceForParam(it.type, classRef, type) }
                    .toTypedArray()

                return constructor.call(*arguments)
            } catch (e: Throwable) {
                e.printStackTrace()
                // no-op. We catch any possible error here that might occur during class creation
            }
        }

        throw NoUsableConstructor()
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun makeStandardInstanceOrNull(classRef: KClass<*>, type: KType) = when (classRef) {
        Int::class -> fixture.int()
        Long::class -> fixture.long()
        Double::class -> fixture.double()
        Float::class -> fixture.float()
//        Char::class -> makeRandomChar(random)
//        String::class -> makeRandomString(random)
        String::class -> fixture.string()
        List::class, Collection::class -> makeRandomList(classRef, type)
        Map::class -> makeRandomMap(classRef, type)
        else -> null
    }

    private fun makeRandomInstanceForParam(
        paramType: KType,
        classRef: KClass<*>,
        type: KType
    ): Any? {
        val classifier = paramType.classifier
        return when (classifier) {
            is KClass<*> -> fixture.make(classifier, paramType)
            is KTypeParameter -> {
                val typeParameterName = classifier.name
                val typeParameterId =
                    classRef.typeParameters.indexOfFirst { it.name == typeParameterName }
                val parameterType = type.arguments[typeParameterId].type ?: getKType<Any>()
//                makeRandomInstance(parameterType.classifier as KClass<*>, parameterType)
                fixture.make(parameterType.classifier as KClass<*>, parameterType)
            }
//            is KClass<*> -> makeRandomInstance(classifier, paramType)
//            is KTypeParameter -> {
//                val typeParameterName = classifier.name
//                val typeParameterId =
//                    classRef.typeParameters.indexOfFirst { it.name == typeParameterName }
//                val parameterType = type.arguments[typeParameterId].type ?: getKType<Any>()
//                makeRandomInstance(parameterType.classifier as KClass<*>, parameterType)
//            }
            else -> throw Error("Type of the classifier $classifier is not supported")
        }
    }

    private fun makeRandomList(classRef: KClass<*>, type: KType): List<Any?> {
        val numOfElements = fixture.int(
            config.possibleCollectionSizes.first,
            config.possibleCollectionSizes.last + 1
        )
        val elemType = type.arguments[0].type!!
        return (1..numOfElements)
            .map { makeRandomInstanceForParam(elemType, classRef, type) }
    }

    private fun makeRandomMap(classRef: KClass<*>, type: KType): Map<Any?, Any?> {
        val numOfElements = fixture.int(
            config.possibleCollectionSizes.first,
            config.possibleCollectionSizes.last + 1
        )
        val keyType = type.arguments[0].type!!
        val valType = type.arguments[1].type!!
        val keys = (1..numOfElements)
            .map { makeRandomInstanceForParam(keyType, classRef, type) }
        val values = (1..numOfElements)
            .map { makeRandomInstanceForParam(valType, classRef, type) }
        return keys.zip(values).toMap()
    }

    private fun makeRandomChar(random: Random) = ('A'..'z').random(random)
    private fun makeRandomString(random: Random) =
        (1..random.nextInt(config.possibleStringSizes.first, config.possibleStringSizes.last + 1))
            .map { makeRandomChar(random) }
            .joinToString(separator = "") { "$it" }

}