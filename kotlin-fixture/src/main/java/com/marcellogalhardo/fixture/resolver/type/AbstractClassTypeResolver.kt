package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.AbstractClassNotSupportedException
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.resolver.SimpleResolver
import com.marcellogalhardo.fixture.typeIsAbstractClass
import kotlin.reflect.KClass

internal class AbstractClassTypeResolver : SimpleResolver() {
    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        if (typeIsAbstractClass) {
            throw AbstractClassNotSupportedException()
        }
        return null
    }

    private val KClass<*>.isAbstractClass: Boolean
        get() = !javaObjectType.isInterface && isAbstract
}