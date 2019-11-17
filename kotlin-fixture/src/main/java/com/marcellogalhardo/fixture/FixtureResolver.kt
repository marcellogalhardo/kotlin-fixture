package com.marcellogalhardo.fixture

import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureResolver {

    fun resolve(creator: FixtureCreator, context: FixtureContext): Any?
}

fun FixtureResolver.resolve(
    creator: FixtureCreator,
    context: FixtureContext.Type,
    paramType: KType
): Any? = context.run {
    return resolve(creator, FixtureContext.Param(classRef, classType, paramType))
}

fun FixtureResolver.resolve(
    creator: FixtureCreator,
    classRef: KClass<*>,
    classType: KType, paramType: KType
): Any? {
    return resolve(creator, FixtureContext.Param(classRef, classType, paramType))
}

fun FixtureResolver.resolve(
    creator: FixtureCreator,
    classRef: KClass<*>,
    classType: KType
): Any? {
    return resolve(creator, FixtureContext.Type(classRef, classType))
}