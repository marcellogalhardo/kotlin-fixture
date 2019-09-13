package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.AbstractClassNotSupportedException
import com.marcellogalhardo.fixture.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class AbstractClassTypeResolver : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        if (classRef.isAbstractClass) {
            throw AbstractClassNotSupportedException()
        }
        return null
    }

    private val  KClass<*>.isAbstractClass:  Boolean
            get() = !javaObjectType.isInterface && isAbstract
}