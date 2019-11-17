package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.internal.resolver.CompositeResolver
import com.marcellogalhardo.fixture.internal.resolver.param.ClassParamResolver
import com.marcellogalhardo.fixture.internal.resolver.param.TypeParamResolver
import com.marcellogalhardo.fixture.internal.resolver.type.AbstractClassTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.BooleanTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.CharTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.ClassTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.DoubleTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.FloatTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.IntTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.InterfaceTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.ListTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.LongTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.MapTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.ObjectTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.SealedClassTypeResolver
import com.marcellogalhardo.fixture.internal.resolver.type.StringTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureResolver {

    fun resolve(creator: FixtureCreator, context: FixtureContext): Any?
}

fun FixtureResolver.resolve(
    creator: FixtureCreator,
    context: FixtureContext.Type,
    paramType: KType
): Any? = context.run {
    return resolve(creator, FixtureContext.Param(classRef, classType, paramType))
}

fun FixtureResolver.resolve(
    creator: FixtureCreator,
    classRef: KClass<*>,
    classType: KType, paramType: KType
): Any? {
    return resolve(creator, FixtureContext.Param(classRef, classType, paramType))
}

fun FixtureResolver.resolve(
    creator: FixtureCreator,
    classRef: KClass<*>,
    classType: KType
): Any? {
    return resolve(creator, FixtureContext.Type(classRef, classType))
}

@Suppress("FunctionName")
fun FixtureResolver(
    creator: FixtureCreator,
    configs: FixtureConfigs
): CompositeResolver {
    val paramResolvers =
        CompositeResolver(
            ClassParamResolver(),
            TypeParamResolver()
        )
    return CompositeResolver(
        BooleanTypeResolver(),
        CharTypeResolver(
            configs
        ),
        DoubleTypeResolver(
            configs
        ),
        FloatTypeResolver(),
        IntTypeResolver(
            configs
        ),
        LongTypeResolver(
            configs
        ),
        StringTypeResolver(),
        ListTypeResolver(
            configs,
            paramResolvers
        ),
        MapTypeResolver(
            configs,
            paramResolvers
        ),
        AbstractClassTypeResolver(),
        InterfaceTypeResolver(),
        ObjectTypeResolver(),
        SealedClassTypeResolver(),
        ClassTypeResolver(
            paramResolvers
        )
    )
}