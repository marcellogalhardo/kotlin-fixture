package com.marcellogalhardo.fixture.internal.resolver.type

import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.internal.resolver.SimpleResolver
import com.marcellogalhardo.fixture.typeIsInterface
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinFunction

internal class InterfaceTypeResolver : SimpleResolver() {

    override fun resolveType(creator: FixtureCreator, context: FixtureContext.Type): Any? = context.run {
        if (typeIsInterface) {
            val javaClass = classRef.javaObjectType
            return Proxy.newProxyInstance(
                javaClass.classLoader,
                arrayOf(javaClass)
            ) { _: Any?, method: Method?, _: Array<out Any?>? ->
                val methodReturnType = method?.kotlinFunction?.returnType?.jvmErasure
                return@newProxyInstance when {
                    methodReturnType != null -> creator.create(methodReturnType, classType)
                    else -> null
                }
            }
        }
        return null
    }
}