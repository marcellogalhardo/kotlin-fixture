package com.marcellogalhardo.fixture.resolver.type

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.FixtureResolver
import com.marcellogalhardo.fixture.utils.TestClass
import com.marcellogalhardo.fixture.utils.TestClassWithObjectParam
import com.marcellogalhardo.fixture.utils.TestObject
import com.marcellogalhardo.fixture.utils.TestResolver
import org.junit.Before
import org.junit.Test
import kotlin.reflect.full.createType
import kotlin.reflect.full.starProjectedType

class ClassTypeResolverTest {

    private lateinit var testResolver: TestResolver

    private lateinit var sut: FixtureResolver

    @Before
    fun setup() {
        testResolver = TestResolver()
        sut = ClassTypeResolver(testResolver)
    }

    @Test
    fun resolve_shouldReturnParam_whenGivenClass() {
        val classRef = TestClass::class

        val result = sut.resolve(
            classRef.javaObjectType.kotlin,
            classRef.javaObjectType.kotlin.createType()
        )

        assertThat(result)
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun resolve_shouldReturnParam_whenGivenClassWithObjectParam() {
        testResolver.result = TestObject
        val classRef = TestClassWithObjectParam::class

        val result = sut.resolve(
            classRef.javaObjectType.kotlin,
            classRef.javaObjectType.kotlin.createType()
        )

        assertThat(result)
            .isInstanceOf(TestClassWithObjectParam::class.java)
        assertThat((result as TestClassWithObjectParam).testParam)
            .isInstanceOf(TestObject::class.java)

    }

    @Test
    fun resolve_shouldReturnNull_whenNotGivenClass() {
        val classRef = Unit

        val result = sut.resolve(
            classRef::class,
            classRef::class.starProjectedType
        )

        assertThat(result)
            .isNull()
    }

}