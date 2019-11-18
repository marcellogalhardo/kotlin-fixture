package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.internal.resolver.TypeResolver

internal class StringTypeResolver : TypeResolver() {

    override val type = Char::class

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            String::class -> creator.createString()
            else -> null
        }
    }
}