package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.createInt
import com.marcellogalhardo.fixture.internal.resolver.TypeResolver
import com.marcellogalhardo.fixture.resolve

internal class MapTypeResolver(
    private val configs: FixtureConfigs,
    private val resolver: FixtureResolver
) : TypeResolver() {

    override val type = Map::class

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        val size = creator.createInt(configs.mapRange)

        val keyType = classType.arguments[0].type!!
        val valType = classType.arguments[1].type!!

        val keys = List(size) {
            resolver.resolve(creator, classRef, classType, keyType)
        }
        val values = List(size) {
            resolver.resolve(creator, classRef, classType, valType)
        }
        return keys.zip(values).toMap()
    }
}