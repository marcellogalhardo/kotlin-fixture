package com.marcellogalhardo.fixture.resolver.param

import com.marcellogalhardo.fixture.ClassifierNotSupportedException
import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KType

class CompositeParamResolver(
    private val resolvers: LinkedList<FixtureParamResolver>
) : FixtureParamResolver {

    override fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any? {
        for (resolver in resolvers) {
            val instance = resolver.resolve(classRef, classType, paramType)
            if (instance != null) {
                return instance
            }
        }
        throw ClassifierNotSupportedException(paramType.classifier)
    }
}