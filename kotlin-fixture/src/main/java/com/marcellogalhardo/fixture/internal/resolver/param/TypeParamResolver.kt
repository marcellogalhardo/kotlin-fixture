package com.marcellogalhardo.fixture.internal.resolver.param

import com.marcellogalhardo.fixture.FixtureBuilder
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.internal.getKType
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import kotlin.reflect.KClass
import kotlin.reflect.KTypeParameter

class TypeParamResolver(
    private val builder: FixtureBuilder
) : SimpleResolver() {

    override fun resolveParam(context: FixtureContext.Param): Any? = context.run {
        return when (val classifier = paramType.classifier) {
            is KTypeParameter -> {
                val typeParameterName = classifier.name
                val typeParameterId = classRef.typeParameters
                    .indexOfFirst { it.name == typeParameterName }
                val parameterType = classType.arguments[typeParameterId].type
                    ?: getKType<Any>()
                builder.next(
                    parameterType.classifier as KClass<*>,
                    parameterType
                )
            }
            else -> null
        }
    }
}