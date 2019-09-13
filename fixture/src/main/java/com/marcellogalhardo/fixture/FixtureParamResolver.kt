package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.external.getKType
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter

interface FixtureParamResolver {

    fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any?

    class Default(
        private val nextFunction: NextFunction
    ) : FixtureParamResolver {
        override fun resolve(classRef: KClass<*>, classType: KType, paramType: KType): Any? {
            return when (val classifier = paramType.classifier) {
                is KClass<*> -> {
                    nextFunction(classifier, paramType)
                }
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
                else -> {
                    throw Error("Type of the classifier $classifier is not supported")
                }
            }
        }

    }
}

@Suppress("FunctionName")
fun FixtureParamResolver(nextFunction: NextFunction): FixtureParamResolver =
    FixtureParamResolver.Default(nextFunction)