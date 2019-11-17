package com.marcellogalhardo.fixture.internal.resolver.type

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.resolve
import com.marcellogalhardo.fixture.utils.TestInterface
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.reflect.full.createType
import kotlin.reflect.full.starProjectedType

class InterfaceTypeResolverTest {

    private lateinit var sut: FixtureResolver

    @Before
    fun setup() {
        sut =
            InterfaceTypeResolver()
    }

    @Test
    fun resolve_shouldReturnParam_whenGivenInterface() {
        val creator = mockk<FixtureCreator>()
        val classRef = TestInterface::class

        val result = sut.resolve(
            creator,
            classRef.javaObjectType.kotlin,
            classRef.javaObjectType.kotlin.createType()
        )

        assertTrue(result is TestInterface)
    }

    @Test
    fun resolve_shouldReturnNull_whenNotGivenInterface() {
        val creator = mockk<FixtureCreator>()
        val classRef = Unit

        val result = sut.resolve(
            creator,
            classRef::class,
            classRef::class.starProjectedType
        )

        assertThat(result)
            .isNull()
    }

}