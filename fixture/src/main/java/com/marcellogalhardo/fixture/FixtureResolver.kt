package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver
import com.marcellogalhardo.fixture.resolver.type.*
import kotlin.reflect.KClass
import kotlin.reflect.KType

class FixtureResolver(
    private val nextFunction: NextFunction,
    private val fixtureConfigs: FixtureConfigs,
    private val fixtureRandom: FixtureRandom,
    private val fixtureParamResolver: FixtureParamResolver = FixtureParamResolver(nextFunction),
    private val resolvers: List<FixtureTypeResolver> = listOf(
        StandardTypeResolver(fixtureRandom),
        CollectionTypeResolver(
            fixtureConfigs,
            fixtureRandom,
            fixtureParamResolver
        ),
        MapTypeResolver(
            fixtureConfigs,
            fixtureRandom,
            fixtureParamResolver
        ),
        AbstractClassTypeResolver(),
        InterfaceTypeResolver(nextFunction),
        ObjectTypeResolver(),
        SealedClassTypeResolver(nextFunction),
        ClassTypeResolver(fixtureParamResolver)
    )
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        for (resolver in resolvers) {
            val instance = resolver.resolve(classRef, typeRef)
            if (instance != null) {
                return instance
            }
        }
        throw NoUsableConstructor()
    }
}