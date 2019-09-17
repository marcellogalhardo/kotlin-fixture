package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal typealias ProviderFunction<T> = (FixtureBuilder) -> T

class DefaultFixture internal constructor(
    configs: FixtureConfigs,
    private val random: FixtureRandom = FixtureRandom()
) : Fixture, FixtureRandom by random {

    private val customTypeMap: HashMap<KClass<*>, ProviderFunction<*>> = hashMapOf()

    private val typeResolver: FixtureTypeResolver = FixtureTypeResolver(this, configs)

    override fun <T : Any> register(
        classRef: KClass<T>,
        providerFunction: ProviderFunction<T>
    ) {
        customTypeMap[classRef] = providerFunction
    }

    override fun next(classRef: KClass<*>, typeRef: KType): Any? {
        if (typeRef.isMarkedNullable) return null

        return customTypeMap[classRef]?.invoke(this)
            ?: typeResolver.resolve(classRef, typeRef)
    }
}