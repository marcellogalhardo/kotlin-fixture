package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureParamResolver
import com.marcellogalhardo.fixture.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KVisibility

internal class ClassTypeResolver(
    private val paramResolver: FixtureParamResolver
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        val constructors = classRef.constructors
            .filter { it.visibility != KVisibility.PRIVATE }
            .sortedBy { it.parameters.size }

        for (constructor in constructors) {
            if (constructor.visibility != KVisibility.PRIVATE) {
                val arguments = constructor.parameters
                    .map { paramResolver.resolve(classRef, typeRef, it.type) }
                    .toTypedArray()
                return constructor.call(*arguments)
            }
        }
        return null
    }
}