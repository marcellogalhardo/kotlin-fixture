package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureParamResolver
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class CollectionTypeResolver(
    private val fixtureConfigs: FixtureConfigs,
    private val fixtureRandom: FixtureRandom,
    private val fixtureParamResolver: FixtureParamResolver
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        return when (classRef) {
            List::class, Collection::class -> {
                val numOfElements = fixtureRandom.nextInt(fixtureConfigs.collectionRange)

                val paramType = typeRef.arguments[0].type!!

                return (1..numOfElements)
                    .map { fixtureParamResolver.resolve(classRef, typeRef, paramType) }
            }
            else -> null
        }
    }
}