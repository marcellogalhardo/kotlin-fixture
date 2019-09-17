package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureBuilder
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
fun FixtureParamResolver(builder: FixtureBuilder): FixtureParamResolver {
    val paramResolvers = linkedListOf(
        ClassParamResolver(builder),
        TypeParamResolver(builder)
    )
    return CompositeParamResolver(paramResolvers)
}