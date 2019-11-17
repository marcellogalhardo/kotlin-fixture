package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.internal.DefaultRandom

interface FixtureRandom {

    fun createBoolean(): Boolean

    fun createChar(): Char

    fun createChar(range: CharRange): Char

    fun createDouble(): Double

    fun createDouble(until: Double): Double

    fun createDouble(from: Double, until: Double): Double

    fun createFloat(): Float

    fun createInt(): Int

    fun createInt(until: Int): Int

    fun createInt(from: Int, until: Int): Int

    fun createLong(): Long

    fun createLong(until: Long): Long

    fun createLong(from: Long, until: Long): Long

    fun createString(): String

    fun createString(prefix: String): String
}

fun FixtureRandom.createDouble(
    range: IntRange
): Double = createDouble(range.first.toDouble(), range.last.toDouble())

fun FixtureRandom.createInt(
    range: IntRange
): Int = createInt(range.first, range.last)

fun FixtureRandom.createLong(
    range: LongRange
): Long = createLong(range.first, range.last)

@Suppress("FunctionName")
fun FixtureRandom(): FixtureRandom =
    DefaultRandom()