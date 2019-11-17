package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.createDouble
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver

internal class DoubleTypeResolver(
    private val configs: FixtureConfigs
) : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Double::class -> creator.createDouble(configs.defaultRange)
            else -> null
        }
    }
}