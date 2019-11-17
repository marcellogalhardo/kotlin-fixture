package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.internal.getKType
import kotlin.reflect.KClass
import kotlin.reflect.KType

interface FixtureCreator : FixtureRandom {

    fun create(classRef: KClass<*>, classType: KType): Any?
}

inline fun <reified T : Any> FixtureCreator.create(): T {
    val kType = getKType<T>()
    return create(T::class, kType) as T
}

inline fun <reified T : Any> FixtureCreator.createListOf(): List<T> = create()

inline fun <reified T : Any> FixtureCreator.createListOf(size: Int): List<T> = List(size) {
    create<T>()
}