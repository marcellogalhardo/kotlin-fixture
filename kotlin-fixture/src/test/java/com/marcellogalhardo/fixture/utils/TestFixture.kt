package com.marcellogalhardo.fixture.utils

import com.marcellogalhardo.fixture.Fixture
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.FixtureResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

class TestFixture(
    private val random: FixtureRandom = FixtureRandom()
) : Fixture, FixtureRandom by random {

    var customTypeResolver: FixtureResolver? = null
    var classRef: KClass<*>? = null
    var typeRef: KType? = null
    var result: Any? = Any()

    override fun <T : Any> register(classRef: KClass<T>, resolver: FixtureResolver) {
        this.customTypeResolver = resolver
    }

    override fun create(classRef: KClass<*>, classType: KType): Any? {
        this.classRef = classRef
        this.typeRef = classType
        return result
    }

}