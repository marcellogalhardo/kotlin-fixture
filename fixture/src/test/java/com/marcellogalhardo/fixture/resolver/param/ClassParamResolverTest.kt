package com.marcellogalhardo.fixture.resolver.param

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.register
import com.marcellogalhardo.fixture.utils.TestFixture
import org.junit.Before
import org.junit.Test
import kotlin.reflect.full.starProjectedType

class ClassParamResolverTest {

    private lateinit var testFixture: TestFixture

    private lateinit var sut: FixtureResolver

    @Before
    fun setup() {
        testFixture = TestFixture()
        sut = ClassParamResolver(testFixture)
    }

    @Test
    fun resolve_shouldReturnParam_whenGivenClassParam() {
        val classRef = 1
        val paramRef = "param"
        testFixture.register {
            paramRef
        }

        val result = sut.resolve(
            classRef::class,
            classRef::class.starProjectedType,
            paramRef::class.starProjectedType
        )

        assertThat(result)
            .isEqualTo(paramRef)
    }

}