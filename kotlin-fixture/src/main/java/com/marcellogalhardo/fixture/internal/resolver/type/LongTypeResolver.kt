package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.createLong
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.internal.resolver.TypeResolver

internal class LongTypeResolver(
    private val configs: FixtureConfigs
) : TypeResolver() {

    override val type = Long::class

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return creator.createLong(configs.longRange)
    }
}