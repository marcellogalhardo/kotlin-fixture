package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.resolver.SimpleResolver

internal class IntTypeResolver(
    private val configs: FixtureConfigs,
    private val random: FixtureRandom
) : SimpleResolver() {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Int::class -> random.nextInt(configs.defaultRange)
            else -> null
        }
    }
}