package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.internal.DefaultRandom

interface FixtureRandom {

    fun nextBoolean(): Boolean

    fun nextChar(): Char

    fun nextChar(range: CharRange): Char

    fun nextDouble(): Double

    fun nextDouble(until: Double): Double

    fun nextDouble(from: Double, until: Double): Double

    fun nextDouble(range: IntRange): Double = nextDouble(
        range.first.toDouble(),
        range.last.toDouble()
    )

    fun nextFloat(): Float

    fun nextInt(): Int

    fun nextInt(until: Int): Int

    fun nextInt(from: Int, until: Int): Int

    fun nextInt(range: IntRange): Int = nextInt(range.first, range.last)

    fun nextLong(): Long

    fun nextLong(until: Long): Long

    fun nextLong(from: Long, until: Long): Long

    fun nextLong(range: LongRange): Long = nextLong(range.first, range.last)

    fun nextString(): String

    fun nextString(prefix: String): String
}

@Suppress("FunctionName")
fun FixtureRandom(): FixtureRandom =
    DefaultRandom()