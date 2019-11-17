package com.marcellogalhardo.fixture.internal.resolver.composite

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver
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

class DefaultTypeResolver(
    private val resolvers: MutableList<FixtureResolver> = mutableListOf()
) : FixtureResolver, MutableList<FixtureResolver> by resolvers {

    constructor(vararg elements: FixtureResolver) : this(mutableListOf(*elements))

    override fun resolve(creator: FixtureCreator, context: FixtureContext): Any? {
        for (resolver in resolvers) {
            val instance = resolver.resolve(creator, context)
            if (instance != null) {
                return instance
            }
        }
        return null
    }
}


@Suppress("FunctionName")
fun DefaultTypeResolver(
    configs: FixtureConfigs
): DefaultTypeResolver {
    val paramResolvers =
        DefaultTypeResolver(
            ClassParamResolver(),
            TypeParamResolver()
        )
    return DefaultTypeResolver(
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