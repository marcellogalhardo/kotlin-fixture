package com.marcellogalhardo.fixture

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FixtureRandomTest {

    @Test
    fun nextBoolean_shouldReturnRandom() {
        val sut = FixtureRandom()

        val result = sut.createBoolean()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Boolean::class.javaObjectType)
        }
    }

    @Test
    fun nextChar_shouldReturnRandom() {
        val sut = FixtureRandom()

        val result = sut.createChar()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Char::class.javaObjectType)
        }
    }

    @Test
    fun nextDouble_shouldReturnRandom() {
        val sut = FixtureRandom()

        val result = sut.createDouble()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Double::class.javaObjectType)
        }
    }

    @Test
    fun nextDouble_shouldReturnRandom_whenGivenUntil() {
        val sut = FixtureRandom()

        val result = sut.createDouble(5.0)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Double::class.javaObjectType)
            isAtMost(5.0)
        }
    }

    @Test
    fun nextDouble_shouldReturnRandom_whenGivenFromAndUntil() {
        val sut = FixtureRandom()

        val result = sut.createDouble(0.0, 5.0)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Double::class.javaObjectType)
            isAtLeast(0.0)
            isAtMost(5.0)
        }
    }

    @Test
    fun nextFloat_shouldReturnRandom() {
        val sut = FixtureRandom()

        val result = sut.createFloat()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Float::class.javaObjectType)
        }
    }

    @Test
    fun nextInt_shouldReturnRandom() {
        val sut = FixtureRandom()

        val result = sut.createInt()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Int::class.javaObjectType)
        }
    }

    @Test
    fun nextInt_shouldReturnRandom_whenGivenUntil() {
        val sut = FixtureRandom()

        val result = sut.createInt(5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Int::class.javaObjectType)
            isAtMost(5)
        }
    }

    @Test
    fun nextInt_shouldReturnRandom_whenGivenFromAndUntil() {
        val sut = FixtureRandom()

        val result = sut.createInt(0, 5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Int::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextInt_shouldReturnRandom_whenGivenRange() {
        val sut = FixtureRandom()

        val result = sut.createInt(0..5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Int::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextLong_shouldReturnRandom() {
        val sut = FixtureRandom()

        val result = sut.createLong()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
        }
    }


    @Test
    fun nextLong_shouldReturnRandom_whenGivenUntil() {
        val sut = FixtureRandom()

        val result = sut.createLong(5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
            isAtMost(5)
        }
    }

    @Test
    fun nextLong_shouldReturnRandom_whenGivenFromAndUntil() {
        val sut = FixtureRandom()

        val result = sut.createLong(0, 5)

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextLong_shouldReturnRandom_whenGivenLongRange() {
        val sut = FixtureRandom()

        val result = sut.createLong(0.toLong()..5.toLong())

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(Long::class.javaObjectType)
            isAtLeast(0)
            isAtMost(5)
        }
    }

    @Test
    fun nextString_shouldReturnRandom() {
        val sut = FixtureRandom()

        val result = sut.createString()

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(String::class.java)
            isNotEmpty()
        }
    }

    @Test
    fun nextString_shouldReturnRandom_whenGivenPrefix() {
        val sut = FixtureRandom()

        val result = sut.createString("prefix")

        assertThat(result).apply {
            isNotNull()
            isInstanceOf(String::class.java)
            isNotEmpty()
            startsWith("prefix")
        }
    }

}