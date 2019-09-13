package com.marcellogalhardo.fixture

import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureTypeResolver {

    fun resolve(classRef: KClass<*>, typeRef: KType): Any?
}