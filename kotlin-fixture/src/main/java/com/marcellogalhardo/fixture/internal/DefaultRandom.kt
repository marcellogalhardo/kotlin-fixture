package com.marcellogalhardo.fixture.internal

import com.marcellogalhardo.fixture.FixtureRandom
import java.util.*
import kotlin.random.Random

class DefaultRandom internal constructor(
    private val random: Random = Random.Default
) : FixtureRandom {

    override fun nextBoolean(): Boolean = random.nextBoolean()

    override fun nextChar(): Char = ('A'..'z').random(random)

    override fun nextChar(range: CharRange): Char = range.random(random)

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