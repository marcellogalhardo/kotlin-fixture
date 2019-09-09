package com.mgalhardo.fixture.provider

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CustomTypeProviderTest {

    @Test
    fun register_nextOfReturnsRegisteredObject() {
        val sut = CustomTypeProvider.Default<String>()
        sut.register {
            "Test"
        }

        val result = sut.nextOf()

        assertThat(result)
            .isEqualTo("Test")
    }

    @Test
    fun register_nextOfKeyReturnsRegisteredObjectUnderKey() {
        val sut = CustomTypeProvider.Default<String>()
        sut.register("key") {
            "Test"
        }

        val result = sut.nextOf("key")

        assertThat(result)
            .isEqualTo("Test")
    }

}