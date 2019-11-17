package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.SealedClassWithoutSubClassesException
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.typeIsSealedClass

internal class SealedClassTypeResolver : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        if (typeIsSealedClass) {
            val sealedSubClass = classRef.sealedSubclasses.firstOrNull()
                ?: throw SealedClassWithoutSubClassesException()

            return creator.create(sealedSubClass, classType)
        }
        return null
    }
}