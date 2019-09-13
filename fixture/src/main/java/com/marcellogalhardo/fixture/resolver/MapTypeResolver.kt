package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureParamResolver
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class MapTypeResolver(
    private val fixtureConfigs: FixtureConfigs,
    private val fixtureRandom: FixtureRandom,
    private val fixtureParamResolver: FixtureParamResolver
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        return when (classRef) {
            Map::class -> {
                val numOfElements = fixtureRandom.nextInt(fixtureConfigs.collectionRange)

                val keyType = typeRef.arguments[0].type!!
                val valType = typeRef.arguments[1].type!!

                val keys = (1..numOfElements)
                    .map { fixtureParamResolver.resolve(classRef, typeRef, keyType) }
                val values = (1..numOfElements)
                    .map { fixtureParamResolver.resolve(classRef, typeRef, valType) }
                return keys.zip(values).toMap()
            }
            else -> null
        }
    }
}