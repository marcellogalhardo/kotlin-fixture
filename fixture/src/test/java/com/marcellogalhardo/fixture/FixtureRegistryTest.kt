package com.marcellogalhardo.fixture

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class FixtureRegistryTest {

    private lateinit var sut: Fixture

    @Before
    fun setup() {
        sut = Fixture()
    }

    @Test
    fun register_shouldKeepObjectCreation() {
        val person = "Marcello Galhardo"
        sut.register {
            person
        }

        val result = sut.next<String>()

        Truth.assertThat(result)
            .isEqualTo(person)
    }

    @Test
    fun register_shouldReplaceObjectCreation() {
        val name1 = "Marcello"
        val name2 = "Galhardo"
        sut.register {
            name1
        }
        sut.register {
            name2
        }

        val result = sut.next<String>()

        Truth.assertThat(result)
            .isEqualTo(name2)
    }

}