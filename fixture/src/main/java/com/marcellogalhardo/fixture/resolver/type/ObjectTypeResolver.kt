package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver
import kotlin.reflect.full.createInstance

internal class ObjectTypeResolver : FixtureResolver.Type {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        if (classRef.constructors.isEmpty()) {
            return classRef.objectInstance ?: classRef.createInstance()
        }
        return null
    }
}