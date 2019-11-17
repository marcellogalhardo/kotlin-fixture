package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.createInt
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.resolve

internal class ListTypeResolver(
    private val configs: FixtureConfigs,
    private val resolver: FixtureResolver
) : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            List::class, Collection::class -> {
                val size = creator.createInt(configs.listRange)
                val paramType = classType.arguments[0].type!!

                return List(size) {
                    resolver.resolve(creator, classRef, classType, paramType)
                }
            }
            else -> null
        }
    }
}