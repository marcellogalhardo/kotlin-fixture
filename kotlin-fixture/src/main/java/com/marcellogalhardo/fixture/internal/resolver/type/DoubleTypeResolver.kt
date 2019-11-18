package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.createDouble
import com.marcellogalhardo.fixture.internal.resolver.TypeResolver

internal class DoubleTypeResolver(
    private val configs: FixtureConfigs
) : TypeResolver() {

    override val type = Double::class

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return creator.createDouble(configs.defaultRange)
    }
}