package com.marcellogalhardo.fixture

import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

internal typealias ProviderMap = HashMap<String, ProviderFunction<Any>>

internal typealias TypeMap = HashMap<String, ProviderMap>

internal const val NO_KEY = ""

interface FixtureTypeMap {

    fun <T : Any> put(classRef: KClass<T>, key: String, providerFunction: ProviderFunction<T>)

    fun <T : Any> get(classRef: KClass<T>, key: String): ProviderFunction<T>?

    class Default internal constructor(
        private val dataSet: TypeMap = hashMapOf()
    ) : FixtureTypeMap {

        override fun <T : Any> put(
            classRef: KClass<T>,
            key: String,
            providerFunction: ProviderFunction<T>
        ) {
            val provider = dataSet.getOrPut(classRef.jvmName) {
                hashMapOf()
            }
            provider[key] = providerFunction
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : Any> get(classRef: KClass<T>, key: String): ProviderFunction<T>? {
            return dataSet[classRef.jvmName]?.get(key) as? ProviderFunction<T>
        }
    }
}

@Suppress("FunctionName")
fun FixtureTypeMap(): FixtureTypeMap = FixtureTypeMap.Default()