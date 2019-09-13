package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.external.getKType
import com.marcellogalhardo.fixture.provider.CustomTypeProvider
import com.marcellogalhardo.fixture.provider.ReflectTypeProvider
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmName

internal typealias NextFunction = (classRef: KClass<*>, type: KType) -> Any?

internal typealias ProviderFunction<T> = (Fixture) -> T

/**
 * Provides object creation services.
 */
interface Fixture : FixtureRandom {

    fun <T : Any> register(classRef: KClass<T>, providerFunction: ProviderFunction<T>)

    fun <T : Any> register(key: String, classRef: KClass<T>, providerFunction: ProviderFunction<T>)

    /**
     * Creates a new instance based on a [KClass] and [KType].
     */
    @Throws(FixtureException::class)
    fun next(classRef: KClass<*>, typeRef: KType): Any?

    @Throws(FixtureException::class)
    fun next(key: String, classRef: KClass<*>, typeRef: KType): Any?

    class Default internal constructor(
        private val fixtureRandom: FixtureRandom = FixtureRandom.Default()
    ) : Fixture, FixtureRandom by fixtureRandom {

        private val reflectTypeProvider: ReflectTypeProvider =
            ReflectTypeProvider(this::next, fixtureRandom)

        private val customTypeProvider = hashMapOf<String, CustomTypeProvider<out Any>>()

        override fun <T : Any> register(
            classRef: KClass<T>,
            providerFunction: ProviderFunction<T>
        ) {
            register("", classRef, providerFunction)
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : Any> register(
            key: String,
            classRef: KClass<T>,
            providerFunction: ProviderFunction<T>
        ) {
            val provider = customTypeProvider.getOrPut(classRef.jvmName) {
                CustomTypeProvider.Default<T>()
            } as CustomTypeProvider<T>
            provider.register(key) {
                providerFunction(this)
            }
        }

        override fun next(classRef: KClass<*>, typeRef: KType): Any? {
            return next("", classRef, typeRef)
        }

        override fun next(key: String, classRef: KClass<*>, typeRef: KType): Any? {
            val typeGenerator = customTypeProvider[classRef.jvmName]
            if (typeGenerator != null) {
                return typeGenerator.nextOf(key)
            }
            return reflectTypeProvider.nextRandomInstance(classRef, typeRef)
        }
    }

}

fun Fixture() = Fixture.Default()

fun Fixture(apply: Fixture.() -> Unit) = Fixture.Default().apply(apply)

inline fun <reified T : Any> Fixture.register(noinline providerFunction: ProviderFunction<T>) {
    register(T::class, providerFunction)
}

inline fun <reified T : Any> Fixture.register(
    key: String,
    noinline providerFunction: (Fixture) -> T
) {
    register(key, T::class, providerFunction)
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