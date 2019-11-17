package com.marcellogalhardo.fixture.internal.resolver.param

import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import kotlin.reflect.KClass

class ClassParamResolver(
    private val creator: FixtureCreator
) : SimpleResolver() {

    override fun resolveParam(context: FixtureContext.Param): Any? = context.run {
        return when (val classifier = paramType.classifier) {
            is KClass<*> -> creator.create(classifier, context.paramType)
            else -> null
        }
    }
}