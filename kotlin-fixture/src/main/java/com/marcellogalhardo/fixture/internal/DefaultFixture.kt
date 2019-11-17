package com.marcellogalhardo.fixture.internal

import com.marcellogalhardo.fixture.*
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal typealias ProviderFunction<T> = (FixtureBuilder) -> T

internal class DefaultFixture constructor(
    configs: FixtureConfigs,
    private val random: FixtureRandom = FixtureRandom()
) : Fixture, FixtureRandom by random {

    private val customTypeMap: HashMap<KClass<*>, ProviderFunction<*>> = hashMapOf()

    private val typeResolver: FixtureResolver =
        FixtureResolver(this, configs)

    override fun <T : Any> register(
        classRef: KClass<T>,
        providerFunction: ProviderFunction<T>
    ) {
        customTypeMap[classRef] = providerFunction
    }

    override fun next(classRef: KClass<*>, classType: KType): Any? {
        return customTypeMap[classRef]?.invoke(this) ?: typeResolver.resolve(classRef, classType)
    }
}