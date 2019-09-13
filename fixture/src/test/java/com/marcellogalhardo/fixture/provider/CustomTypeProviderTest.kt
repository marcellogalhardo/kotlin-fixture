package com.marcellogalhardo.fixture.provider

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CustomTypeProviderTest {

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