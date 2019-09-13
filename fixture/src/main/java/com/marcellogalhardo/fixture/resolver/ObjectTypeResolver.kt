package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createInstance

internal class ObjectTypeResolver : FixtureTypeResolver  {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        if (classRef.constructors.isEmpty()) {
            return classRef.objectInstance ?: classRef.createInstance()
        }
        return null
    }
}