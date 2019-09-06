package com.mgalhardo.fixture.generator

import kotlin.random.Random

interface FloatGenerator {

    fun float(): Float

    class Default(
        private val random: Random = Random.Default
    ) : FloatGenerator {

        override fun float(): Float = random.nextFloat()
    }
}