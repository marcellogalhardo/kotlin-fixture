package com.marcellogalhardo.fixture.external

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GetKTypeTest {

    @Test
    fun getKType_shouldKType_whenGivenListWith_withNestedTypes() {
        val result = getKType<List<Map<String, Array<Double>>>>()

        assertThat(result.toString())
            .isEqualTo("kotlin.collections.List<kotlin.collections.Map<kotlin.String, kotlin.Array<kotlin.Double>>>")
    }

    @Test
    fun getKType_shouldKType_whenGivenListType() {
        val result = getKType<List<*>>()

        assertThat(result.toString())
            .isEqualTo("kotlin.collections.List<kotlin.Any>")
    }

    @Test
    fun getKType_shouldKType_whenGivenArrayType() {
        val result = getKType<Array<*>>()

        assertThat(result.toString())
            .isEqualTo("kotlin.Array<kotlin.Any>")
    }

    @Test
    fun getKType_shouldKType_whenGivenNestedArrayOfStrings() {
        val result = getKType<Array<Array<String>>>()

        assertThat(result.toString())
            .isEqualTo("kotlin.Array<kotlin.Array<kotlin.String>>")
    }

    @Test
    fun getKType_shouldKType_whenGivenUnit() {
        val result = getKType<Unit>()

        assertThat(result.toString())
            .isEqualTo("kotlin.Unit")
    }
}