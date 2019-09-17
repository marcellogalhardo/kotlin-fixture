package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.external.getKType
import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal typealias NextFunction = (classRef: KClass<*>, type: KType) -> Any?

internal typealias ProviderFunction<T> = (Fixture) -> T

interface Fixture : FixtureRandom {

    fun <T : Any> register(classRef: KClass<T>, providerFunction: ProviderFunction<T>)

    fun next(classRef: KClass<*>, typeRef: KType): Any?

    class Default internal constructor(
        configs: FixtureConfigs,
        private val random: FixtureRandom = FixtureRandom()
    ) : Fixture, FixtureRandom by random {

        private val typeMap: HashMap<KClass<*>, ProviderFunction<*>> = hashMapOf()

        private val typeResolver: FixtureTypeResolver = FixtureTypeResolver(
            configs,
            random,
            ::next
        )

        override fun <T : Any> register(
            classRef: KClass<T>,
            providerFunction: ProviderFunction<T>
        ) {
            typeMap[classRef] = providerFunction
        }

        override fun next(classRef: KClass<*>, typeRef: KType): Any? {
            if (typeRef.isMarkedNullable) return null

            return typeMap[classRef]?.invoke(this)
                ?: typeResolver.resolve(classRef, typeRef)
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

inline fun <reified T : Any> Fixture.next(): T {
    val kType = getKType<T>()
    return next(T::class, kType) as T
}

inline fun <reified T : Any> Fixture.nextListOf(size: Int): List<T> = List(size) {
    next<T>()
}