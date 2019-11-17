package com.marcellogalhardo.fixture.internal.resolver.param

import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.internal.getKType
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import kotlin.reflect.KClass
import kotlin.reflect.KTypeParameter

class TypeParamResolver : SimpleResolver() {

    override fun resolveParam(creator: FixtureCreator, context: FixtureContext.Param): Any? = context.run {
        return when (val classifier = paramType.classifier) {
            is KTypeParameter -> {
                val typeParameterName = classifier.name
                val typeParameterId = classRef.typeParameters
                    .indexOfFirst { it.name == typeParameterName }
                val parameterType = classType.arguments[typeParameterId].type
                    ?: getKType<Any>()
                creator.create(
                    parameterType.classifier as KClass<*>,
                    parameterType
                )
            }
            else -> null
        }
    }
}