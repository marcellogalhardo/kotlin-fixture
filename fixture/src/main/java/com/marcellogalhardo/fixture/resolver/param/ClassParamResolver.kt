package com.marcellogalhardo.fixture.resolver.param

import com.marcellogalhardo.fixture.NextFunction
import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

class ClassParamResolver(
    private val nextFunction: NextFunction
) : FixtureParamResolver {

    override fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any? {
        return when (val classifier = paramType.classifier) {
            is KClass<*> -> nextFunction(classifier, paramType)
            else -> null
        }
    }
}