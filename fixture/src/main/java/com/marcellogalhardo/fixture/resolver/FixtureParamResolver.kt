package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.NextFunction
import com.marcellogalhardo.fixture.linkedListOf
import com.marcellogalhardo.fixture.resolver.param.ClassParamResolver
import com.marcellogalhardo.fixture.resolver.param.CompositeParamResolver
import com.marcellogalhardo.fixture.resolver.param.TypeParamResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureParamResolver {

    fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any?
}

@Suppress("FunctionName")
fun FixtureParamResolver(nextFunction: NextFunction): FixtureParamResolver {
    val paramResolvers = linkedListOf(
        ClassParamResolver(nextFunction),
        TypeParamResolver(nextFunction)
    )
    return CompositeParamResolver(paramResolvers)
}