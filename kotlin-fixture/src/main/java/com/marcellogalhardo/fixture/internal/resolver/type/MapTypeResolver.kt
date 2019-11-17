package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver

internal class MapTypeResolver(
    private val random: FixtureRandom,
    private val configs: FixtureConfigs,
    private val resolver: FixtureResolver
) : SimpleResolver() {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Map::class -> {
                val size = random.nextInt(configs.mapRange)

                val keyType = classType.arguments[0].type!!
                val valType = classType.arguments[1].type!!

                val keys = List(size) {
                    resolver.resolve(classRef, classType, keyType)
                }
                val values = List(size) {
                    resolver.resolve(classRef, classType, valType)
                }
                return keys.zip(values).toMap()
            }
            else -> null
        }
    }
}