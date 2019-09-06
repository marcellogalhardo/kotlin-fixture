package com.mgalhardo.fixture.generator

import kotlin.random.Random

interface BooleanGenerator {

    fun boolean(): Boolean

    class Default(
        private val random: Random = Random.Default
    ) : BooleanGenerator {

        override fun boolean(): Boolean = random.nextBoolean()
    }
}