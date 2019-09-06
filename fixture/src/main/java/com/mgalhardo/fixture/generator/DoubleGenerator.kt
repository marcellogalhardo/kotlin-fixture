package com.mgalhardo.fixture.generator

import kotlin.random.Random

interface DoubleGenerator {

    fun double(): Double

    fun double(until: Double): Double

    fun double(from: Double, until: Double): Double

    class Default(
        private val random: Random = Random.Default
    ) : DoubleGenerator {

        override fun double(): Double = random.nextDouble()

        override fun double(until: Double): Double = random.nextDouble(until)

        override fun double(from: Double, until: Double): Double =
            random.nextDouble(from, until)
    }
}