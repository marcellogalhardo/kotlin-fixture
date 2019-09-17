package com.marcellogalhardo.fixture

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FixtureRandomTest {

    @Test
    fun nextBoolean_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextBoolean()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Boolean::class.javaObjectType)
        }
    }

    @Test
    fun nextChar_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextChar()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Char::class.javaObjectType)
        }
    }

    @Test
    fun nextDouble_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextDouble()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Double::class.javaObjectType)
        }
    }

    @Test
    fun nextDouble_withUntil_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextDouble(5.0)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Double::class.javaObjectType)
            isAtMost(5.0)
        }
    }

    @Test
    fun nextDouble_withFromUntil_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextDouble(0.0, 5.0)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Double::class.javaObjectType)
            isAtLeast(0.0)
            isAtMost(5.0)
        }
    }

    @Test
    fun nextFloat_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextFloat()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Float::class.javaObjectType)
        }
    }

    @Test
    fun nextInt_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextInt()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Int::class.javaObjectType)
        }
    }

    @Test
    fun nextInt_withUntil_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextInt(5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Int::class.javaObjectType)
            isAtMost(5)
        }
    }

    @Test
    fun nextInt_withFromUntil_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextInt(0, 5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Int::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextInt_withIntRange_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextInt(0..5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Int::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextLong_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextLong()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
        }
    }


    @Test
    fun nextLong_withUntil_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextLong(5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
            isAtMost(5)
        }
    }

    @Test
    fun nextLong_withFromUntil_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextLong(0, 5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextLong_withIntRange_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextLong(0..5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextLong_withLongRange_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextLong(0.toLong()..5.toLong())

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextString_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextString()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(String::class.java)
            isNotEmpty()
        }
    }

    @Test
    fun nextString_withPrefix_returnsRandom() {
        val sut = FixtureRandom()

        val result = sut.nextString("prefix")

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(String::class.java)
            isNotEmpty()
            contains("prefix")
        }
    }

}