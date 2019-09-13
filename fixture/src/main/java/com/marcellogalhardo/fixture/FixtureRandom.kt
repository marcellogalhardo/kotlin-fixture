package com.marcellogalhardo.fixture

import java.util.*
import kotlin.random.Random

interface FixtureRandom {

    fun nextAny(): Any

    fun nextBoolean(): Boolean

    fun nextChar(): Char

    fun nextDouble(): Double

    fun nextDouble(until: Double): Double

    fun nextDouble(from: Double, until: Double): Double

    fun nextFloat(): Float

    fun nextInt(): Int

    fun nextInt(until: Int): Int

    fun nextInt(from: Int, until: Int): Int

    fun nextInt(range: IntRange): Int = nextInt(range.first, range.last)

    fun nextLong(): Long

    fun nextLong(until: Long): Long

    fun nextLong(from: Long, until: Long): Long

    fun nextLong(range: IntRange): Long = nextLong(range.first.toLong(), range.last.toLong())

    fun nextLong(range: LongRange): Long = nextLong(range.first, range.last)

    fun nextString(): String

    fun nextString(prefix: String): String

    class Default internal constructor(
        private val random: Random = Random.Default
    ) : FixtureRandom {

        override fun nextAny(): Any = Any()

        override fun nextBoolean(): Boolean = random.nextBoolean()

        override fun nextChar(): Char = ('A'..'z').random(random)

        override fun nextDouble(): Double = random.nextDouble()

        override fun nextDouble(until: Double): Double = random.nextDouble(until)

        override fun nextDouble(
            from: Double,
            until: Double
        ): Double = random.nextDouble(from, until)

        override fun nextFloat(): Float = random.nextFloat()

        override fun nextInt(): Int = random.nextInt()

        override fun nextInt(until: Int): Int = random.nextInt(until)

        override fun nextInt(from: Int, until: Int): Int = random.nextInt(from, until)

        override fun nextLong(): Long = random.nextLong()

        override fun nextLong(until: Long): Long = random.nextLong(until)

        override fun nextLong(from: Long, until: Long): Long = random.nextLong(from, until)

        override fun nextString(): String = UUID.randomUUID().toString()

        override fun nextString(prefix: String): String = "$prefix-${nextString()}"
    }
}

@Suppress("FunctionName")
fun FixtureRandom(): FixtureRandom = FixtureRandom.Default()