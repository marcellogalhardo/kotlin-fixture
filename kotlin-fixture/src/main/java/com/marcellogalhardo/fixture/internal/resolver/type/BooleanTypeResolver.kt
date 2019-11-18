package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.internal.resolver.TypeResolver

internal class BooleanTypeResolver : TypeResolver() {

    override val type = Boolean::class

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return creator.createBoolean()
    }
}