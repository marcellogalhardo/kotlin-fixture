package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.TypeNotSupportedException
import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class CompositeTypeResolver(
    private val resolvers: LinkedList<FixtureTypeResolver>
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        for (resolver in resolvers) {
            val instance = resolver.resolve(classRef, typeRef)
            if (instance != null) {
                return instance
            }
        }
        throw TypeNotSupportedException(classRef)
    }
}