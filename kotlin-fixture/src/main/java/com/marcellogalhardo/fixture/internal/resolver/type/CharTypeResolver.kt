package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver

internal class CharTypeResolver(
    private val configs: FixtureConfigs,
    private val random: FixtureRandom
) : SimpleResolver() {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Char::class -> random.nextChar(configs.charRange)
            else -> null
        }
    }
}