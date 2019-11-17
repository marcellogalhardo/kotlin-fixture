package com.marcellogalhardo.fixture

import kotlin.reflect.KClass

internal typealias FixtureResolverFunction<T> = FixtureCreator.() -> T

interface FixtureRegistry {

    fun <T : Any> register(classRef: KClass<T>, resolver: FixtureResolver)
}

inline fun <reified T : Any> FixtureRegistry.register(noinline resolverFunction: FixtureResolverFunction<T>) {
    val resolver = object : FixtureResolver {
        override fun resolve(creator: FixtureCreator, context: FixtureContext): Any? {
            return if (context.classRef == T::class) {
                 resolverFunction(creator)
            } else null
        }
    }
    register(T::class, resolver)
}