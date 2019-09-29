package com.marcellogalhardo.fixture

import kotlin.reflect.KClass
import kotlin.reflect.KType

sealed class FixtureContext {

    abstract val classRef: KClass<*>
    abstract val classType: KType

    data class Type(
        override val classRef: KClass<*>,
        override val classType: KType
    ) : FixtureContext()

    data class Param(
        override val classRef: KClass<*>,
        override val classType: KType,
        val paramType: KType
    ) : FixtureContext()
}

internal val FixtureContext.typeIsSealedClass
    get() = classRef.isSealed

internal val FixtureContext.typeIsAbstractClass
    get() = classRef.isAbstract && !typeIsInterface

internal val FixtureContext.typeIsInterface
    get() = classRef.javaObjectType.isInterface

internal val FixtureContext.typeIsClass
    get() = !typeIsSealedClass && !typeIsAbstractClass && !typeIsInterface && classRef.constructors.isNotEmpty()

internal val FixtureContext.typeIsObject
    get() = !typeIsSealedClass && !typeIsAbstractClass && !typeIsInterface && classRef.constructors.isEmpty()


