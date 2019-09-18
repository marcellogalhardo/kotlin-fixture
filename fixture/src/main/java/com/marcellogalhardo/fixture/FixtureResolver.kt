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

    interface Type : FixtureResolver {

        override fun resolve(context: FixtureContext): Any? {
            return if (context is FixtureContext.Type) {
                resolveType(context)
            } else {
                null
            }
        }

        fun resolveType(context: FixtureContext.Type): Any?
    }

    interface Param : FixtureResolver {

        override fun resolve(context: FixtureContext): Any? {
            return if (context is FixtureContext.Param) {
                resolveParam(context)
            } else {
                null
            }
        }

        fun resolveParam(context: FixtureContext.Param): Any?
    }
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
    val typeResolvers = CompositeResolver(
        BooleanTypeResolver(builder),
        CharTypeResolver(builder),
        DoubleTypeResolver(builder),
        FloatTypeResolver(builder),
        IntTypeResolver(builder),
        LongTypeResolver(builder),
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
    val resolvers = typeResolvers.union(typeResolvers).toList()
    return CompositeResolver(resolvers)
}