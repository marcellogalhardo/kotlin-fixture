package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.resolve
import com.marcellogalhardo.fixture.typeIsClass
import kotlin.reflect.KVisibility

internal class ClassTypeResolver(
    private val resolver: FixtureResolver
) : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        if (typeIsClass) {
            val constructors = classRef.constructors
                .filter { it.visibility != KVisibility.PRIVATE }
                .sortedBy { it.parameters.size }

            for (constructor in constructors) {
                val arguments = constructor.parameters
                    .map { resolver.resolve(creator, classRef, classType, it.type) }
                    .toTypedArray()
                return constructor.call(*arguments)
            }
        }
        return null
    }
}