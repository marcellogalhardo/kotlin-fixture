package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver

abstract class SimpleResolver : FixtureResolver {

    final override fun resolve(context: FixtureContext): Any? {
        return when (context) {
            is FixtureContext.Type -> resolveType(context)
            is FixtureContext.Param -> resolveParam(context)
        }
    }

    open fun resolveType(context: FixtureContext.Type): Any? = null

    open fun resolveParam(context: FixtureContext.Param): Any? = null
}