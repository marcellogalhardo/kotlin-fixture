package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureBuilder
import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.linkedListOf
import com.marcellogalhardo.fixture.resolver.type.*
import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureTypeResolver {

    fun resolve(classRef: KClass<*>, typeRef: KType): Any?
}

@Suppress("FunctionName")
fun FixtureTypeResolver(
    builder: FixtureBuilder,
    configs: FixtureConfigs
): FixtureTypeResolver {
    val paramResolver = FixtureParamResolver(builder)
    val typeResolvers = linkedListOf(
        StandardTypeResolver(builder),
        CollectionTypeResolver(
            configs,
            builder,
            paramResolver
        ),
        MapTypeResolver(
            configs,
            builder,
            paramResolver
        ),
        AbstractClassTypeResolver(),
        InterfaceTypeResolver(builder),
        ObjectTypeResolver(),
        SealedClassTypeResolver(builder),
        ClassTypeResolver(paramResolver)
    )
    return CompositeTypeResolver(typeResolvers)
}