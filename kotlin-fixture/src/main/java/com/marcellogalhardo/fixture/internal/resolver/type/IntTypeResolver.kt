package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.createInt
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver

internal class IntTypeResolver(
    private val configs: FixtureConfigs
) : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Int::class -> creator.createInt(configs.defaultRange)
            else -> null
        }
    }
}