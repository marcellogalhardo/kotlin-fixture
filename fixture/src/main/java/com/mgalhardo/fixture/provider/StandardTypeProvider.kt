package com.mgalhardo.fixture.provider

import java.util.*
import kotlin.random.Random


interface StandardTypeProvider {

    fun nextAny(): Any

    fun nextBits(bitCount: Int): Int

    fun nextBoolean(): Boolean

    fun nextBytes(
        array: ByteArray,
        fromIndex: Int = 0,
        toIndex: Int = array.size
    ): ByteArray

    fun nextBytes(array: ByteArray): ByteArray

    fun nextBytes(size: Int): ByteArray

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

    fun nextString(): String

    fun nextString(prefix: String): String

    class Default(
        private val random: Random = Random.Default
    ) : StandardTypeProvider {

        override fun nextAny(): Any {
            return when (nextInt(0, 6)) {
                0 -> nextBoolean()
                1 -> nextChar()
                2 -> nextDouble()
                3 -> nextFloat()
                4 -> nextLong()
                5 -> nextString()
                else -> nextInt()
            }
        }

        override fun nextBits(bitCount: Int): Int = random.nextBits(bitCount)

        override fun nextBoolean(): Boolean = random.nextBoolean()

        override fun nextBytes(
            array: ByteArray,
            fromIndex: Int,
            toIndex: Int
        ): ByteArray = random.nextBytes(array, fromIndex, toIndex)

        override fun nextBytes(array: ByteArray): ByteArray = random.nextBytes(array)

        override fun nextBytes(size: Int): ByteArray = random.nextBytes(size)

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