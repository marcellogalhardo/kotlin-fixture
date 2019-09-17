package com.marcellogalhardo.fixture.resolver.param

import com.marcellogalhardo.fixture.NextFunction
import com.marcellogalhardo.fixture.external.getKType
import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter

class TypeParamResolver(
    private val nextFunction: NextFunction
) : FixtureParamResolver {

    override fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any? {
        return when (val classifier = paramType.classifier) {
            is KTypeParameter -> {
                val typeParameterName = classifier.name
                val typeParameterId =
                    classRef.typeParameters.indexOfFirst { it.name == typeParameterName }
                val parameterType = classType.arguments[typeParameterId].type ?: getKType<Any>()
                nextFunction(
                    parameterType.classifier as KClass<*>,
                    parameterType
                )
            }
            else -> null
        }
    }
}