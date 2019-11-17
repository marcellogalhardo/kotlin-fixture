@file:Suppress("unused")

package com.marcellogalhardo.fixture

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.utils.*
import org.junit.Before
import org.junit.Test

class FixtureCreatorTest {

    private lateinit var sut: FixtureCreator

    @Before
    fun setup() {
        sut = Fixture()
    }

    @Test
    fun next_shouldReturnRandom_whenGivenObject() {
        val result = sut.create<TestObject>()

        assertThat(result)
            .isInstanceOf(TestObject::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenInterface() {
        val result = sut.create<TestInterface>()

        assertThat(result)
            .isInstanceOf(TestInterface::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenInvokeAnyMethodOfProxyInterface() {
        val proxyInterface = sut.create<TestInterface>()

        val result = proxyInterface.echo()

        assertThat(result).apply {
            isInstanceOf(String::class.java)
            isNotEmpty()
            isNotNull()
        }
    }

    @Test
    fun next_shouldReturnRandom_whenGivenSealedClass_withAtLeastOneSubclass() {
        val result = sut.create<TestSealedClassWithSubClasses>()

        assertThat(result)
            .isInstanceOf(TestSealedClassWithSubClasses::class.java)
    }

    @Test
    fun next_shouldThrow_whenGivenSealedClass_withoutSubClasses() {
        var error: Throwable? = null
        try {
            sut.create<TestSealedClassWithoutSubClasses>()
        } catch (ex: Throwable) {
            error = ex
        }

        assertThat(error)
            .isInstanceOf(SealedClassWithoutSubClassesException::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass() {
        val result = sut.create<TestClass>()

        assertThat(result)
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClassWithGenerics() {
        val result = sut.create<TestClassWithGenerics<String>>()

        assertThat(result)
            .isInstanceOf(TestClassWithGenerics::class.java)

        assertThat(result.testParam)
            .isInstanceOf(String::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenDataClass() {
        val result = sut.create<TestDataClass>()

        assertThat(result)
            .isInstanceOf(TestDataClass::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenBoolean() {
        val result = sut.create<Boolean>()

        assertThat(result)
            .isInstanceOf(Boolean::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenChar() {
        val result = sut.create<Char>()

        assertThat(result)
            .isInstanceOf(Char::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenDouble() {
        val result = sut.create<Double>()

        assertThat(result)
            .isInstanceOf(Double::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenFloat() {
        val result = sut.create<Float>()

        assertThat(result)
            .isInstanceOf(Float::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenInt() {
        val result = sut.create<Int>()

        assertThat(result)
            .isInstanceOf(Integer::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenLong() {
        val result = sut.create<Long>()

        assertThat(result)
            .isInstanceOf(Long::class.javaObjectType)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenString() {
        val result = sut.create<String>()

        assertThat(result)
            .isInstanceOf(String::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withObjectParam() {
        val result = sut.create<TestClassWithObjectParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithObjectParam::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withInterfaceParam() {
        val result = sut.create<TestClassWithInterfaceParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithInterfaceParam::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withSealedClassParam() {
        val result = sut.create<TestClassWithSealedClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithSealedClassParam::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withClassParam() {
        val result = sut.create<TestClassWithClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithClassParam::class.java)
    }

    @Test
    fun next_shouldReturnRandom_whenGivenClass_withDataClassParam() {
        val result = sut.create<TestClassWithDataClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithDataClassParam::class.java)
    }

    @Test
    fun next_shouldThrow_whenGivenClass_withoutConstructors() {
        var error: Throwable? = null
        try {
            sut.create<TestClassWithPrivateConstructor>()
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
            sut.create<TestAbstractClass>()
        } catch (ex: Throwable) {
            error = ex
        }

        assertThat(error)
            .isInstanceOf(AbstractClassNotSupportedException::class.java)
    }

    @Test
    fun nextListOf_shouldReturnRandomList() {
        val result = sut.createListOf<TestClass>()

        assertThat(result).apply {
            isInstanceOf(List::class.java)
            isNotEmpty()
        }
        assertThat(result.first())
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun nextListOf_shouldReturnRandomList_givenSize() {
        val result = sut.createListOf<TestClass>(5)

        assertThat(result).apply {
            isInstanceOf(List::class.java)
            hasSize(5)
        }
        assertThat(result.first())
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun next_shouldReturnRandomMap_givenMap() {
        val result = sut.create<Map<Int, String>>()

        assertThat(result).apply {
            isInstanceOf(Map::class.javaObjectType)
            isNotEmpty()
        }
        assertThat(result.keys.first())
            .isInstanceOf(Int::class.javaObjectType)
        assertThat(result.values.first())
            .isInstanceOf(String::class.java)
    }
}