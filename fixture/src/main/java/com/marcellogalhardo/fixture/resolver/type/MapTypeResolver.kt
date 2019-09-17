package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class MapTypeResolver(
    private val fixtureRandom: FixtureRandom,
    private val fixtureConfigs: FixtureConfigs,
    private val fixtureParamResolver: FixtureParamResolver
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        return when (classRef) {
            Map::class -> {
                val size = fixtureRandom.nextInt(fixtureConfigs.collectionRange)

                val keyType = typeRef.arguments[0].type!!
                val valType = typeRef.arguments[1].type!!

                val keys = List(size) {
                    fixtureParamResolver.resolve(classRef, typeRef, keyType)
                }
                val values = List(size) {
                    fixtureParamResolver.resolve(classRef, typeRef, valType)
                }
                return keys.zip(values).toMap()
            }
            else -> null
        }
    }
}