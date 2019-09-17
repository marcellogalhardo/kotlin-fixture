package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class ListTypeResolver(
    private val random: FixtureRandom,
    private val configs: FixtureConfigs,
    private val paramResolver: FixtureParamResolver
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        return when (classRef) {
            List::class, Collection::class -> {
                val size = random.nextInt(configs.collectionRange)
                val paramType = typeRef.arguments[0].type!!

                return List(size) {
                    paramResolver.resolve(classRef, typeRef, paramType)
                }
            }
            else -> null
        }
    }
}