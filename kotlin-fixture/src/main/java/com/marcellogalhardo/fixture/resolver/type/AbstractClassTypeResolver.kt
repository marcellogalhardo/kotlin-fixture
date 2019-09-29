package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.AbstractClassNotSupportedException
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.resolver.SimpleResolver
import com.marcellogalhardo.fixture.typeIsAbstractClass

internal class AbstractClassTypeResolver : SimpleResolver() {
    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        if (typeIsAbstractClass) {
            throw AbstractClassNotSupportedException()
        }
        return null
    }
}