package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.ContextNotSupported
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver

class CompositeResolver(
    private val resolvers: List<FixtureResolver>
) : FixtureResolver, List<FixtureResolver> by resolvers {

    constructor(vararg elements: FixtureResolver) : this(listOf(*elements))

    override fun resolve(context: FixtureContext): Any? {
        for (resolver in resolvers) {
            val instance = resolver.resolve(context)
            if (instance != null) {
                return instance
            }
        }
        throw ContextNotSupported(context)
    }
}