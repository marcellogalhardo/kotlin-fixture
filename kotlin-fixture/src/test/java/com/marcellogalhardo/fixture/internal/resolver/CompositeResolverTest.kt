package com.marcellogalhardo.fixture.internal.resolver

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.ContextNotSupported
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.internal.resolver.CompositeResolver
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class CompositeResolverTest {

    @Test
    fun resolve_shouldReturnInstance_givenResolversCanHandle() {
        val context = mockk<FixtureContext>()
        val resolver = mockk<FixtureResolver>()
        every { resolver.resolve(context) } answers { Any() }

        val result = CompositeResolver(
            resolver
        )
            .resolve(context)

        verify { resolver.resolve(context) }
        assertThat(result).isInstanceOf(Any::class.java)
    }

    @Test
    fun resolve_shouldThrowException_givenResolversCanNotHandle() {
        val context = mockk<FixtureContext>()
        val resolver = mockk<FixtureResolver>()
        every { resolver.resolve(context) } answers { null }

        try {
            CompositeResolver(
                resolver
            ).resolve(context)
        } catch (throwable: Throwable) {
            assertThat(throwable).isInstanceOf(ContextNotSupported::class.java)
        }
    }
}