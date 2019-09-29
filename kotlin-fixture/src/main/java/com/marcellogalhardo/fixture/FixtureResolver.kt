package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.resolver.CompositeResolver
import com.marcellogalhardo.fixture.resolver.param.ClassParamResolver
import com.marcellogalhardo.fixture.resolver.param.TypeParamResolver
import com.marcellogalhardo.fixture.resolver.type.*
import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureResolver {

    fun resolve(classRef: KClass<*>, classType: KType): Any? {
        val typeContext = FixtureContext.Type(classRef, classType)
        return resolve(typeContext)
    }

    fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any? {
        val typeContext = FixtureContext.Param(classRef, classType, paramType)
        return resolve(typeContext)
    }

    fun resolve(context: FixtureContext.Type, paramType: KType): Any? = context.run {
        val paramContext = FixtureContext.Param(classRef, classType, paramType)
        return resolve(paramContext)
    }

    fun resolve(context: FixtureContext): Any?
}

@Suppress("FunctionName")
fun FixtureResolver(
    builder: FixtureBuilder,
    configs: FixtureConfigs
): CompositeResolver {
    val paramResolvers = CompositeResolver(
        ClassParamResolver(builder),
        TypeParamResolver(builder)
    )
    return CompositeResolver(
        BooleanTypeResolver(builder),
        CharTypeResolver(
            configs,
            builder
        ),
        DoubleTypeResolver(
            configs,
            builder
        ),
        FloatTypeResolver(builder),
        IntTypeResolver(
            configs,
            builder
        ),
        LongTypeResolver(
            configs,
            builder
        ),
        StringTypeResolver(builder),
        ListTypeResolver(
            builder,
            configs,
            paramResolvers
        ),
        MapTypeResolver(
            builder,
            configs,
            paramResolvers
        ),
        AbstractClassTypeResolver(),
        InterfaceTypeResolver(builder),
        ObjectTypeResolver(),
        SealedClassTypeResolver(builder),
        ClassTypeResolver(paramResolvers)
    )
}