package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver
import kotlin.reflect.KVisibility

internal class ClassTypeResolver(
    private val resolver: FixtureResolver
) : FixtureResolver.Type {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        val constructors = classRef.constructors
            .filter { it.visibility != KVisibility.PRIVATE }
            .sortedBy { it.parameters.size }

        for (constructor in constructors) {
            if (constructor.visibility != KVisibility.PRIVATE) {
                val arguments = constructor.parameters
                    .map { resolver.resolve(classRef, classType, it.type) }
                    .toTypedArray()
                return constructor.call(*arguments)
            }
        }
        return null
    }
}