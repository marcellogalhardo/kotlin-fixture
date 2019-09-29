package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.resolver.SimpleResolver
import com.marcellogalhardo.fixture.typeIsObject
import kotlin.reflect.full.createInstance

internal class ObjectTypeResolver : SimpleResolver() {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        if (typeIsObject) {
            return classRef.objectInstance ?: classRef.createInstance()
        }
        return null
    }
}