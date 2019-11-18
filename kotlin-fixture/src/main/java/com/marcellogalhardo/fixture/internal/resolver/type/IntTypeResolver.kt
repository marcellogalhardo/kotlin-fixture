package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.createInt
import com.marcellogalhardo.fixture.internal.resolver.TypeResolver

internal class IntTypeResolver(
    private val configs: FixtureConfigs
) : TypeResolver() {

    override val type = Int::class

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return creator.createInt(configs.defaultRange)
    }
}