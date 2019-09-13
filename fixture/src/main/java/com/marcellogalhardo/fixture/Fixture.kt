package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.external.getKType
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal typealias NextFunction = (classRef: KClass<*>, type: KType) -> Any?

internal typealias ProviderFunction<T> = (Fixture) -> T

/**
 * Provides object creation services.
 */
interface Fixture : FixtureRandom {

    fun <T : Any> register(classRef: KClass<T>, providerFunction: ProviderFunction<T>)

    fun <T : Any> register(classRef: KClass<T>, key: String, providerFunction: ProviderFunction<T>)

    /**
     * Creates a new instance based on a [KClass] and [KType].
     */
    fun next(classRef: KClass<*>, typeRef: KType): Any?

    fun next(key: String, classRef: KClass<*>, typeRef: KType): Any?

    class Default internal constructor(
        fixtureConfigs: FixtureConfigs,
        private val fixtureRandom: FixtureRandom = FixtureRandom()
    ) : Fixture, FixtureRandom by fixtureRandom {

        private val resolver = FixtureResolver(::next, fixtureConfigs, fixtureRandom)

        private val typeMap = FixtureTypeMap()

        override fun <T : Any> register(
            classRef: KClass<T>,
            providerFunction: ProviderFunction<T>
        ) {
            register(classRef, NO_KEY, providerFunction)
        }

        override fun <T : Any> register(
            classRef: KClass<T>,
            key: String,
            providerFunction: ProviderFunction<T>
        ) {
            typeMap.put(classRef, key, providerFunction)
        }

        override fun next(classRef: KClass<*>, typeRef: KType): Any? {
            return next(NO_KEY, classRef, typeRef)
        }

        override fun next(key: String, classRef: KClass<*>, typeRef: KType): Any? {
            if (typeRef.isMarkedNullable) return null

            return typeMap.get(classRef, key)?.invoke(this)
                ?: resolver.resolve(classRef, typeRef)
        }
    }

}

@Suppress("FunctionName")
fun Fixture(configs: FixtureConfigs = FixtureConfigs()): Fixture = Fixture.Default(configs)

@Suppress("FunctionName")
fun Fixture(configs: FixtureConfigs = FixtureConfigs(), apply: Fixture.() -> Unit): Fixture =
    Fixture.Default(configs).apply(apply)

inline fun <reified T : Any> Fixture.register(noinline providerFunction: ProviderFunction<T>) {
    register(T::class, providerFunction)
}

inline fun <reified T : Any> Fixture.register(
    key: String,
    noinline providerFunction: (Fixture) -> T
) {
    register(T::class, key, providerFunction)
}

inline fun <reified T : Any> Fixture.next(): T {
    val kType = getKType<T>()
    return next(T::class, kType) as T
}

inline fun <reified T : Any> Fixture.next(key: String): T {
    val kType = getKType<T>()
    return next(key, T::class, kType) as T
}

inline fun <reified T : Any> Fixture.nextListOf(size: Int): List<T> = List(size) {
    next<T>()
}