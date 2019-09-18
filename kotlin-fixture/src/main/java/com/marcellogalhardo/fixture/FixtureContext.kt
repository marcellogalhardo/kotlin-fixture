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