package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureBuilder
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

internal class InterfaceTypeResolver(
    private val builder: FixtureBuilder
) : FixtureResolver.Type {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
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

                builder.next(methodReturnType, classType)
            }
        }
        return null
    }
}