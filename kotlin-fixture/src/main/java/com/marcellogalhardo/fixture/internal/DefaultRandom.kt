package com.marcellogalhardo.fixture.internal

import com.marcellogalhardo.fixture.FixtureRandom
import java.util.*
import kotlin.random.Random

class DefaultRandom internal constructor(
    private val random: Random = Random.Default
) : FixtureRandom {

    override fun createBoolean(): Boolean = random.nextBoolean()

    override fun createChar(): Char = ('A'..'z').random(random)

    override fun createChar(range: CharRange): Char = range.random(random)

    override fun createDouble(): Double = random.nextDouble()

    override fun createDouble(until: Double): Double = random.nextDouble(until)

    override fun createDouble(
        from: Double,
        until: Double
    ): Double = random.nextDouble(from, until)

    override fun createFloat(): Float = random.nextFloat()

    override fun createInt(): Int = random.nextInt()

    override fun createInt(until: Int): Int = random.nextInt(until)

    override fun createInt(from: Int, until: Int): Int = random.nextInt(from, until)

    override fun createLong(): Long = random.nextLong()

    override fun createLong(until: Long): Long = random.nextLong(until)

    override fun createLong(from: Long, until: Long): Long = random.nextLong(from, until)

    override fun createString(): String = UUID.randomUUID().toString()

    override fun createString(prefix: String): String = "$prefix-${createString()}"
}