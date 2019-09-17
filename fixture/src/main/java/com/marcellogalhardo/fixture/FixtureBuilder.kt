package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.external.getKType
import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureBuilder : FixtureRandom {

    fun next(classRef: KClass<*>, typeRef: KType): Any?
}

inline fun <reified T : Any> FixtureBuilder.next(): T {
    val kType = getKType<T>()
    return next(T::class, kType) as T
}

inline fun <reified T : Any> FixtureBuilder.nextListOf(size: Int): List<T> = List(size) {
    next<T>()
}