package com.marcellogalhardo.fixture.resolver.param

import com.marcellogalhardo.fixture.ClassifierNotSupportedException
import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

class CompositeParamResolver(
    private val classParamResolver: ClassParamResolver,
    private val typeParamResolver: TypeParamResolver
) : FixtureParamResolver {

    override fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any? {
        return classParamResolver.resolve(classRef, classType, paramType)
            ?: typeParamResolver.resolve(classRef, classType, paramType)
            ?: throw ClassifierNotSupportedException(paramType.classifier)
    }
}