package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.AbstractClassNotSupportedException
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver
import kotlin.reflect.KClass

internal class AbstractClassTypeResolver : FixtureResolver.Type {
    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        if (classRef.isAbstractClass) {
            throw AbstractClassNotSupportedException()
        }
        return null
    }

    private val KClass<*>.isAbstractClass: Boolean
        get() = !javaObjectType.isInterface && isAbstract
}