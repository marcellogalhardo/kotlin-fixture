@file:Suppress("unused")

package com.marcellogalhardo.fixture

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.utils.*
import org.junit.Before
import org.junit.Test

class FixtureBuilderTest {

    private lateinit var sut: FixtureBuilder

    @Before
    fun setup() {
        sut = Fixture()
    }

    @Test
    fun next_shouldReturnRandom_whenGivenObject() {
        val result = sut.next<TestObject>()

        assertThat(result)
            .isInstanceOf(TestObject::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenInterface() {
        val result = sut.next<TestInterface>()

        assertThat(result)
            .isInstanceOf(TestInterface::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenSealedClass_withAtLeastOneSubclass() {
        val result = sut.next<TestSealedClassWithSubClasses>()

        assertThat(result)
            .isInstanceOf(TestSealedClassWithSubClasses::class.java)
    }

    @Test
    fun next_shouldThrow_whenGivenSealedClass_withoutSubClasses() {
        var error: Throwable? = null
        try {
            sut.next<TestSealedClassWithoutSubClasses>()
        } catch (ex: Throwable) {
            error = ex
        }

        assertThat(error)
            .isInstanceOf(SealedClassWithoutSubClassesException::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass() {
        val result = sut.next<TestClass>()

        assertThat(result)
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClassWithGenerics() {
        val result = sut.next<TestClassWithGenerics<String>>()

        assertThat(result)
            .isInstanceOf(TestClassWithGenerics::class.java)

        assertThat(result.testParam)
            .isInstanceOf(String::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenDataClass() {
        val result = sut.next<TestDataClass>()

        assertThat(result)
            .isInstanceOf(TestDataClass::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenBoolean() {
        val result = sut.next<Boolean>()

        assertThat(result)
            .isInstanceOf(Boolean::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenChar() {
        val result = sut.next<Char>()

        assertThat(result)
            .isInstanceOf(Char::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenDouble() {
        val result = sut.next<Double>()

        assertThat(result)
            .isInstanceOf(Double::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenFloat() {
        val result = sut.next<Float>()

        assertThat(result)
            .isInstanceOf(Float::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenInt() {
        val result = sut.next<Int>()

        assertThat(result)
            .isInstanceOf(Integer::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenLong() {
        val result = sut.next<Long>()

        assertThat(result)
            .isInstanceOf(Long::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenString() {
        val result = sut.next<String>()

        assertThat(result)
            .isInstanceOf(String::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withObjectParam() {
        val result = sut.next<TestClassWithObjectParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithObjectParam::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withInterfaceParam() {
        val result = sut.next<TestClassWithInterfaceParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithInterfaceParam::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withSealedClassParam() {
        val result = sut.next<TestClassWithSealedClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithSealedClassParam::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withClassParam() {
        val result = sut.next<TestClassWithClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithClassParam::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withDataClassParam() {
        val result = sut.next<TestClassWithDataClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithDataClassParam::class.java)
    }

    @Test
    fun next_shouldThrow_whenGivenClass_withoutConstructors() {
        var error: Throwable? = null
        try {
            sut.next<TestClassWithPrivateConstructor>()
        } catch (ex: Throwable) {
            error = ex
        }

        assertThat(error)
            .isInstanceOf(ContextNotSupported::class.java)
    }

    @Test
    fun next_shouldThrow_whenGivenAbstractClass() {
        var error: Throwable? = null
        try {
            sut.next<TestAbstractClass>()
        } catch (ex: Throwable) {
            error = ex
        }

        assertThat(error)
            .isInstanceOf(AbstractClassNotSupportedException::class.java)
    }
}