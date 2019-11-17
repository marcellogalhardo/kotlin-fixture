package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.typeIsObject
import kotlin.reflect.full.createInstance

internal class ObjectTypeResolver : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        if (typeIsObject) {
            return classRef.objectInstance ?: classRef.createInstance()
        }
        return null
    }
}