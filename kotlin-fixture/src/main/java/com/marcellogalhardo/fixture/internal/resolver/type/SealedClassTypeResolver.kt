package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureBuilder
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.SealedClassWithoutSubClassesException
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.typeIsSealedClass

internal class SealedClassTypeResolver(
    private val builder: FixtureBuilder
) : SimpleResolver() {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        if (typeIsSealedClass) {
            val sealedSubClass = classRef.sealedSubclasses.firstOrNull()
                ?: throw SealedClassWithoutSubClassesException()

            return builder.next(sealedSubClass, classType)
        }
        return null
    }
}