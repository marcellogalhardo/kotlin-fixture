package com.marcellogalhardo.fixture.internal.resolver

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver
import kotlin.reflect.KClass

abstract class TypeResolver : FixtureResolver {

    abstract val type: KClass<*>

    final override fun resolve(creator: FixtureCreator, context: FixtureContext): Any? {
        if (context !is FixtureContext.Type) return null
        return when (context.classRef) {
            type -> resolveType(creator, context)
            else -> null
        }
    }

    abstract fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any?
}