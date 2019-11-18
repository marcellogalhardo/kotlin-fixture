package com.marcellogalhardo.fixture.internal.resolver

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.internal.resolver.composite.DefaultTypeResolver
import com.marcellogalhardo.fixture.resolve
import com.marcellogalhardo.fixture.utils.TestFixture
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class DefaultTypeResolverTest {

    private val testFixture = TestFixture()

    @Test
    fun resolve_shouldReturnInstance_givenResolversCanHandle() {
        val context = mockk<FixtureContext>()
        val resolver = mockk<FixtureResolver>()
        every { resolver.resolve(testFixture, context) } answers { Any() }

        val result = DefaultTypeResolver(
            resolver
        )
            .resolve(testFixture, context)

        verify { resolver.resolve(testFixture, context) }
        assertThat(result).isInstanceOf(Any::class.java)
    }

    @Test
    fun resolve_shouldReturnNull_givenResolversCanNotHandle() {
        val context = mockk<FixtureContext>()
        val resolver = mockk<FixtureResolver>()
        every { resolver.resolve(testFixture, context) } answers { null }


        val result = DefaultTypeResolver(
            resolver
        )
            .resolve(testFixture, context)

        assertThat(result).isNull()
    }
}