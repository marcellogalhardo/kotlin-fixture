package com.marcellogalhardo.fixture.internal.resolver

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver

abstract class SimpleResolver : FixtureResolver {

    final override fun resolve(creator: FixtureCreator, context: FixtureContext): Any? {
        return when (context) {
            is FixtureContext.Type -> resolveType(creator, context)
            is FixtureContext.Param -> resolveParam(creator, context)
        }
    }

    open fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = null

    open fun resolveParam(creator: FixtureCreator, context: FixtureContext.Param): Any? = null
}