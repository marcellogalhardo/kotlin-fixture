package com.marcellogalhardo.fixture

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class FixtureTest {

    @Test
    fun fixture_shouldReturnFixture_withDefaultConfigs() {
        val configs = FixtureConfigs()
        val sut = Fixture()

        val result = sut.nextListOf<Int>()

        assertThat(sut).isInstanceOf(Fixture::class.java)
        assertThat(result.size).apply {
            isAtLeast(configs.collectionRange.first)
            isAtMost(configs.collectionRange.last)
        }
    }

    @Test
    fun fixture_shouldReturnFixture_givenConfigs() {
        val configs = FixtureConfigs(
            collectionRange = 5..10
        )
        val sut = Fixture(configs)

        val result = sut.nextListOf<Int>()

        assertThat(sut).isInstanceOf(Fixture::class.java)
        assertThat(result.size).apply {
            isAtLeast(configs.collectionRange.first)
            isAtMost(configs.collectionRange.last)
        }
    }

    @Test
    fun fixture_shouldReturnFixture_givenApply() {
        val sut = Fixture {
            register {
                5
            }
        }

        val result = sut.next<Int>()

        assertThat(sut).isInstanceOf(Fixture::class.java)
        assertThat(result).isEqualTo(5)
    }

}