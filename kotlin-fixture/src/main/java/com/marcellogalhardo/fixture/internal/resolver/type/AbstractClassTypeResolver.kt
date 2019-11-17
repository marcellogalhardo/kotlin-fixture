package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.AbstractClassNotSupportedException
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.typeIsAbstractClass

internal class AbstractClassTypeResolver : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        if (typeIsAbstractClass) {
            throw AbstractClassNotSupportedException()
        }
        return null
    }
}