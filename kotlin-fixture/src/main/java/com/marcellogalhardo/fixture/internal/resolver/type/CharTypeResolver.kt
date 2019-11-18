package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.internal.resolver.TypeResolver
import kotlin.reflect.KClass

internal class CharTypeResolver(
    private val configs: FixtureConfigs
) : TypeResolver() {

    override val type = Char::class

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        return creator.createChar(configs.charRange)
    }
}