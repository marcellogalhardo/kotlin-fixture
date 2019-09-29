package com.marcellogalhardo.fixture

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FixtureRegistryTest {

    private lateinit var sut: Fixture

    @Before
    fun setup() {
        sut = Fixture()
    }

    @Test
    fun register_shouldDefineCustomCreation() {
        val person = "Marcello Galhardo"
        sut.register {
            person
        }

        val result = sut.next<String>()

        assertThat(result)
            .isEqualTo(person)
    }

    @Test
    fun register_shouldReplaceCustomCreation() {
        val name1 = "Marcello"
        val name2 = "Galhardo"
        sut.register {
            name1
        }
        sut.register {
            name2
        }

        val result = sut.next<String>()

        assertThat(result)
            .isEqualTo(name2)
    }

}