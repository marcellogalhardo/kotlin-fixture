package com.marcellogalhardo.fixture.internal.resolver.param

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.resolve
import com.marcellogalhardo.fixture.utils.TestClassWithGenerics
import com.marcellogalhardo.fixture.utils.TestClassWithObjectParam
import com.marcellogalhardo.fixture.utils.TestFixture
import com.marcellogalhardo.fixture.utils.TestObject
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance
import kotlin.reflect.full.createType

class TypeParamResolverTest {

    private lateinit var testFixture: TestFixture

    private lateinit var sut: FixtureResolver

    @Before
    fun setup() {
        testFixture = TestFixture()
        sut = TypeParamResolver()
    }

    @Test
    fun resolve_shouldReturnParam_whenGivenClassParam() {
        val creator = mockk<FixtureCreator>()
        val classRef = TestClassWithObjectParam(TestObject)
        val parameter = classRef::class.constructors
            .first()
            .parameters
            .first()

        sut.resolve(
            creator,
            classRef::class,
            classRef::class.createType(),
            parameter.type
        )

        assertThat(testFixture.classRef)
            .isNull()
    }

    @Test
    fun resolve_shouldReturnNull_whenGivenTypeParam() {
        val classRef = TestClassWithGenerics("Marcello")
        val parameter = classRef::class.constructors
            .first()
            .parameters
            .first()

        val projection = KTypeProjection(KVariance.INVARIANT, String::class.createType())
        sut.resolve(
            testFixture,
            classRef::class,
            classRef::class.createType(listOf(projection)),
            parameter.type
        )

        assertThat(testFixture.classRef)
            .isEqualTo(String::class)
    }

}