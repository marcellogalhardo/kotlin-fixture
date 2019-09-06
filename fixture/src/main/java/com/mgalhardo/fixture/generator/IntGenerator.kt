package com.mgalhardo.fixture.generator

import kotlin.random.Random

interface IntGenerator {

    fun int(): Int

    fun int(until: Int): Int

    fun int(from: Int, until: Int): Int

    class Default(
        private val random: Random = Random.Default
    ) : IntGenerator {

        override fun int(): Int = random.nextInt()

        override fun int(until: Int): Int = random.nextInt(until)

        override fun int(from: Int, until: Int): Int = random.nextInt(from, until)
    }

}