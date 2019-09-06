package com.mgalhardo.fixture.generator

import kotlin.random.Random

interface BitsGenerator {

    fun bits(bitCount: Int): Int

    class Default(
        private val random: Random = Random.Default
    ) : BitsGenerator {

        override fun bits(bitCount: Int): Int = random.nextBits(bitCount)
    }
}