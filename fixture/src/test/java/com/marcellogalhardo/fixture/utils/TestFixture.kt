package com.marcellogalhardo.fixture.utils

import com.marcellogalhardo.fixture.Fixture
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.ProviderFunction
import kotlin.reflect.KClass
import kotlin.reflect.KType

class TestFixture(
    private val random: FixtureRandom = FixtureRandom()
) : Fixture, FixtureRandom by random {

    val map = hashMapOf<KClass<*>, ProviderFunction<*>>()

    override fun <T : Any> register(classRef: KClass<T>, providerFunction: ProviderFunction<T>) {
        map[classRef] = providerFunction
    }

    override fun next(classRef: KClass<*>, typeRef: KType): Any? {
        return map[classRef]?.invoke(this)
    }

}