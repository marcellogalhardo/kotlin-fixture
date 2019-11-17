package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver

internal class FloatTypeResolver : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            Float::class -> creator.createFloat()
            else -> null
        }
    }
}