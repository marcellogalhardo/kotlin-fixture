@file:Suppress("unused")

package com.mgalhardo.fixture

import com.google.common.truth.Truth.assertThat
import com.mgalhardo.fixture.provider.NoUsableConstructor
import org.junit.Test

class FixtureTest {

    @Test
    fun nextOf_createsObjectType() {
        val sut = Fixture()

        val result = sut.nextOf<TestObject>()

        assertThat(result)
            .isInstanceOf(TestObject::class.java)
    }

    @Test
    fun nextOf_createsInterface() {
        val sut = Fixture()

        val result = sut.nextOf<TestInterface>()

        assertThat(result)
            .isInstanceOf(TestInterface::class.java)
    }

    @Test
    fun nextOf_createsSealedClassWithAtLeastOneSubClass() {
        val sut = Fixture()

        val result = sut.nextOf<TestSealedClass>()

        assertThat(result)
            .isInstanceOf(TestSealedClass::class.java)
    }

    @Test
    fun nextOf_createsClass() {
        val sut = Fixture()

        val result = sut.nextOf<TestClass>()

        assertThat(result)
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun nextOf_createsDataClass() {
        val sut = Fixture()

        val result = sut.nextOf<TestDataClass>()

        assertThat(result)
            .isInstanceOf(TestDataClass::class.java)
    }

    @Test
    fun nextOf_createsBoolean() {
        val sut = Fixture()

        val result = sut.nextOf<Boolean>()

        assertThat(result)
            .isInstanceOf(Boolean::class.javaObjectType)
    }

    @Test
    fun nextOf_createsChar() {
        val sut = Fixture()

        val result = sut.nextOf<Char>()

        assertThat(result)
            .isInstanceOf(Char::class.javaObjectType)
    }

    @Test
    fun nextOf_createsDouble() {
        val sut = Fixture()

        val result = sut.nextOf<Double>()

        assertThat(result)
            .isInstanceOf(Double::class.javaObjectType)
    }

    @Test
    fun nextOf_createsFloat() {
        val sut = Fixture()

        val result = sut.nextOf<Float>()

        assertThat(result)
            .isInstanceOf(Float::class.javaObjectType)
    }

    @Test
    fun nextOf_createsInt() {
        val sut = Fixture()

        val result = sut.nextOf<Int>()

        assertThat(result)
            .isInstanceOf(Integer::class.javaObjectType)
    }

    @Test
    fun nextOf_createsLong() {
        val sut = Fixture()

        val result = sut.nextOf<Long>()

        assertThat(result)
            .isInstanceOf(Long::class.javaObjectType)
    }

    @Test
    fun nextOf_createsString() {
        val sut = Fixture()

        val result = sut.nextOf<String>()

        assertThat(result)
            .isInstanceOf(String::class.java)
    }

    @Test
    fun nextOf_createsClassWithObjectParam() {
        val sut = Fixture()

        val result = sut.nextOf<TestClassWithObjectParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithObjectParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithInterfaceParam() {
        val sut = Fixture()

        val result = sut.nextOf<TestClassWithInterfaceParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithInterfaceParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithSealedClassParam() {
        val sut = Fixture()

        val result = sut.nextOf<TestClassWithSealedClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithSealedClassParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithClassParam() {
        val sut = Fixture()

        val result = sut.nextOf<TestClassWithClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithClassParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithDataClassParam() {
        val sut = Fixture()

        val result = sut.nextOf<TestClassWithDataClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithDataClassParam::class.java)
    }

    @Test
    fun nextOf_throwsNoUsableConstructor() {
        val sut = Fixture()

        var error: Throwable? = null
        try {
            sut.nextOf<TestClassWithPrivateConstructor>()
        } catch (ex: Throwable) {
            error = ex
        }

        assertThat(error)
            .isInstanceOf(NoUsableConstructor::class.java)
    }
}

internal class TestClassWithObjectParam(private val testParam: TestObject)

internal class TestClassWithInterfaceParam(private val testParam: TestInterface)

internal class TestClassWithSealedClassParam(private val testParam: TestSealedClass)

internal class TestClassWithClassParam(private val testParam: TestClass)

internal class TestClassWithDataClassParam(private val testParam: TestDataClass)

internal object TestObject

internal interface TestInterface

internal sealed class TestSealedClass {
    object TestSubClass : TestSealedClass()
}

internal class TestClass

internal class TestClassWithPrivateConstructor private constructor()

internal data class TestDataClass(private val id: Int)