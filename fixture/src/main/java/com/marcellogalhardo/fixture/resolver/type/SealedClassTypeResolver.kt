package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureBuilder
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.SealedClassWithoutSubClassesException

internal class SealedClassTypeResolver(
    private val builder: FixtureBuilder
) : FixtureResolver.Type {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        if (classRef.isSealed) {
            val sealedSubClass = classRef.sealedSubclasses.firstOrNull()
                ?: throw SealedClassWithoutSubClassesException()

            return builder.next(sealedSubClass, classType)
        }
        return null
    }
}