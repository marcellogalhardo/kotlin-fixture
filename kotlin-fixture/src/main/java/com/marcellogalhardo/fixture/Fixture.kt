package com.marcellogalhardo.fixture

import kotlin.reflect.KClass
import kotlin.reflect.KType

//region Fixture
interface Fixture : FixtureRegistry, FixtureBuilder

@Suppress("FunctionName")
fun Fixture(
    configs: FixtureConfigs = FixtureConfigs()
): Fixture = DefaultFixture(configs)

@Suppress("FunctionName")
fun Fixture(
    configs: FixtureConfigs = FixtureConfigs(),
    apply: Fixture.() -> Unit
): Fixture = Fixture(configs).apply(apply)
//endregion

//region FixtureBuilder
interface FixtureBuilder : FixtureRandom {

    fun next(classRef: KClass<*>, classType: KType): Any?
}

inline fun <reified T : Any> FixtureBuilder.next(): T {
    val kType = getKType<T>()
    return next(T::class, kType) as T
}

inline fun <reified T : Any> FixtureBuilder.nextListOf(): List<T> = next()

inline fun <reified T : Any> FixtureBuilder.nextListOf(size: Int): List<T> = List(size) {
    next<T>()
}
//endregion

//region FixtureRegistry
interface FixtureRegistry {

    fun <T : Any> register(classRef: KClass<T>, providerFunction: ProviderFunction<T>)
}

inline fun <reified T : Any> FixtureRegistry.register(noinline providerFunction: ProviderFunction<T>) {
    register(T::class, providerFunction)
}
//endregion