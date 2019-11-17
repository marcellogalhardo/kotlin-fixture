package com.marcellogalhardo.fixture.internal.resolver.type

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.internal.resolver.type.InterfaceTypeResolver
import com.marcellogalhardo.fixture.utils.TestFixture
import com.marcellogalhardo.fixture.utils.TestInterface
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
            InterfaceTypeResolver(
                TestFixture()
            )
    }

    @Test
    fun resolve_shouldReturnParam_whenGivenInterface() {
        val classRef = TestInterface::class

        val result = sut.resolve(
            classRef.javaObjectType.kotlin,
            classRef.javaObjectType.kotlin.createType()
        )

        assertTrue(result is TestInterface)
    }

    @Test
    fun resolve_shouldReturnNull_whenNotGivenInterface() {
        val classRef = Unit

        val result = sut.resolve(
            classRef::class,
            classRef::class.starProjectedType
        )

        assertThat(result)
            .isNull()
    }

}