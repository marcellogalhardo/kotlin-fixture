package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureTypeResolver
import com.marcellogalhardo.fixture.NextFunction
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

internal class InterfaceTypeResolver(
    private val nextFunction: NextFunction
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        val javaClass = classRef.javaObjectType
        if (javaClass.isInterface) {
            return Proxy.newProxyInstance(
                javaClass.classLoader,
                arrayOf(javaClass)
            ) { _: Any, method: Method, _: Array<out Any> ->
                val methodReturnType = method.kotlinFunction
                    ?.returnType
                    ?.jvmErasure
                    ?: return@newProxyInstance null

                nextFunction(methodReturnType, typeRef)
            }
        }
        return null
    }
}