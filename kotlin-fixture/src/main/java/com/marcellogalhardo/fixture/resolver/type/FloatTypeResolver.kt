package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.resolver.SimpleResolver

internal class FloatTypeResolver(
    private val random: FixtureRandom
) : SimpleResolver() {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Float::class -> random.nextFloat()
            else -> null
        }
    }
}