package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal typealias NextFunction = (classRef: KClass<*>, type: KType) -> Any?

internal typealias ProviderFunction<T> = (FixtureBuilder) -> T

interface Fixture : FixtureRegistry, FixtureBuilder {

    class Default internal constructor(
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

}

@Suppress("FunctionName")
fun Fixture(configs: FixtureConfigs = FixtureConfigs()): Fixture = Fixture.Default(configs)

@Suppress("FunctionName")
fun Fixture(configs: FixtureConfigs = FixtureConfigs(), apply: Fixture.() -> Unit): Fixture =
    Fixture.Default(configs).apply(apply)