package com.marcellogalhardo.fixture

import kotlin.reflect.KClass

interface FixtureRegistry {

    fun <T : Any> register(classRef: KClass<T>, providerFunction: ProviderFunction<T>)
}

inline fun <reified T : Any> FixtureRegistry.register(noinline providerFunction: ProviderFunction<T>) {
    register(T::class, providerFunction)
}