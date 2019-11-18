package com.marcellogalhardo.fixture

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

interface FixtureCreator : FixtureRandom {

    fun create(classRef: KClass<*>, classType: KType): Any?
}

@UseExperimental(ExperimentalStdlibApi::class)
inline fun <reified T : Any> FixtureCreator.create(): T {
    val kType = typeOf<T>()
    return create(T::class, kType) as T
}

inline fun <reified T : Any> FixtureCreator.createListOf(): List<T> = create()

inline fun <reified T : Any> FixtureCreator.createListOf(size: Int): List<T> = List(size) {
    create<T>()
}