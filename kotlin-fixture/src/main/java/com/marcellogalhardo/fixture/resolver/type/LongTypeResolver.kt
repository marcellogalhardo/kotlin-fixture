package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.FixtureResolver

internal class LongTypeResolver(
    private val configs: FixtureConfigs,
    private val random: FixtureRandom
) : FixtureResolver.Type {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Long::class -> random.nextLong(configs.longRange)
            else -> null
        }
    }
}