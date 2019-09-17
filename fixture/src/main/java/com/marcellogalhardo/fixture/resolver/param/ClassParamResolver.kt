package com.marcellogalhardo.fixture.resolver.param

import com.marcellogalhardo.fixture.FixtureBuilder
import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

class ClassParamResolver(
    private val builder: FixtureBuilder
) : FixtureParamResolver {

    override fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any? {
        return when (val classifier = paramType.classifier) {
            is KClass<*> -> builder.next(classifier, paramType)
            else -> null
        }
    }
}