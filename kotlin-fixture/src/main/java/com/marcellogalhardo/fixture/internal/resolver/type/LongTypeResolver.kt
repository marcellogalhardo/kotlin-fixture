package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.createLong
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver

internal class LongTypeResolver(
    private val configs: FixtureConfigs,
    private val random: FixtureRandom
) : SimpleResolver() {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Long::class -> random.createLong(configs.longRange)
            else -> null
        }
    }
}