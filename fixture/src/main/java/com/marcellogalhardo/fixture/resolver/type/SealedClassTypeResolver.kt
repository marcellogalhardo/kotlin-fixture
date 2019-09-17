package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureBuilder
import com.marcellogalhardo.fixture.SealedClassWithoutSubClassesException
import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class SealedClassTypeResolver(
    private val builder: FixtureBuilder
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        if (classRef.isSealed) {
            val sealedSubClass = classRef.sealedSubclasses.firstOrNull()
                ?: throw SealedClassWithoutSubClassesException()

            return builder.next(sealedSubClass, typeRef)
        }
        return null
    }
}