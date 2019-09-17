package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.NextFunction
import com.marcellogalhardo.fixture.linkedListOf
import com.marcellogalhardo.fixture.resolver.type.*
import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureTypeResolver {

    fun resolve(classRef: KClass<*>, typeRef: KType): Any?
}

@Suppress("FunctionName")
fun FixtureTypeResolver(
    configs: FixtureConfigs,
    random: FixtureRandom,
    nextFunction: NextFunction
): FixtureTypeResolver {
    val paramResolver = FixtureParamResolver(nextFunction)
    val typeResolvers = linkedListOf(
        StandardTypeResolver(random),
        CollectionTypeResolver(
            configs,
            random,
            paramResolver
        ),
        MapTypeResolver(
            configs,
            random,
            paramResolver
        ),
        AbstractClassTypeResolver(),
        InterfaceTypeResolver(nextFunction),
        ObjectTypeResolver(),
        SealedClassTypeResolver(nextFunction),
        ClassTypeResolver(paramResolver)
    )
    return CompositeTypeResolver(typeResolvers)
}