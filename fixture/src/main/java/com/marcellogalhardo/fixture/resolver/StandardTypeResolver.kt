package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.FixtureTypeResolver
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class StandardTypeResolver(
    private val fixtureRandom: FixtureRandom
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        return when (classRef) {
            Any::class -> fixtureRandom.nextAny()
            Boolean::class -> fixtureRandom.nextBoolean()
            Char::class -> fixtureRandom.nextChar()
            Double::class -> fixtureRandom.nextDouble()
            Float::class -> fixtureRandom.nextFloat()
            Int::class -> fixtureRandom.nextInt()
            Long::class -> fixtureRandom.nextLong()
            String::class -> fixtureRandom.nextString()
            else -> null
        }
    }
}