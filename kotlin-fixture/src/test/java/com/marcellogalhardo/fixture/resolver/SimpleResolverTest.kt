package com.marcellogalhardo.fixture.resolver

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.FixtureContext
import io.mockk.mockk
import org.junit.Test

class SimpleResolverTest {

    private val sut = object : SimpleResolver() {
        val anyType = FixtureContext.Type::class.simpleName
        val anyParam = FixtureContext.Param::class.simpleName

        override fun resolveType(context: FixtureContext.Type) = anyType
        override fun resolveParam(context: FixtureContext.Param) = anyParam
    }

    @Test
    fun resolve_shouldInvokeResolveType_givenTypeContext() {
        val context = mockk<FixtureContext.Type>()

        val result = sut.resolve(context)

        assertThat(result).isEqualTo(sut.anyType)
    }

    @Test
    fun resolve_shouldInvokeResolveType_givenParamContext() {
        val context = mockk<FixtureContext.Param>()

        val result = sut.resolve(context)

        assertThat(result).isEqualTo(sut.anyParam)
    }
}