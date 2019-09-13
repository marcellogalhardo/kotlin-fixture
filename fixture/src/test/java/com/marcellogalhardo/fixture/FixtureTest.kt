@file:Suppress("unused")

package com.marcellogalhardo.fixture

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.provider.NoUsableConstructor
import org.junit.Test

class FixtureTest {

    @Test
    fun nextOf_createsObjectType() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestObject>()

        assertThat(result)
            .isInstanceOf(TestObject::class.java)
    }

    @Test
    fun nextOf_createsInterface() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestInterface>()

        assertThat(result)
            .isInstanceOf(TestInterface::class.java)
    }

    @Test
    fun nextOf_createsSealedClassWithAtLeastOneSubClass() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestSealedClass>()

        assertThat(result)
            .isInstanceOf(TestSealedClass::class.java)
    }

    @Test
    fun nextOf_createsClass() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestClass>()

        assertThat(result)
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun nextOf_createsDataClass() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestDataClass>()

        assertThat(result)
            .isInstanceOf(TestDataClass::class.java)
    }

    @Test
    fun nextOf_createsBoolean() {
        val sut = FixtureFactory.create()

        val result = sut.next<Boolean>()

        assertThat(result)
            .isInstanceOf(Boolean::class.javaObjectType)
    }

    @Test
    fun nextOf_createsChar() {
        val sut = FixtureFactory.create()

        val result = sut.next<Char>()

        assertThat(result)
            .isInstanceOf(Char::class.javaObjectType)
    }

    @Test
    fun nextOf_createsDouble() {
        val sut = FixtureFactory.create()

        val result = sut.next<Double>()

        assertThat(result)
            .isInstanceOf(Double::class.javaObjectType)
    }

    @Test
    fun nextOf_createsFloat() {
        val sut = FixtureFactory.create()

        val result = sut.next<Float>()

        assertThat(result)
            .isInstanceOf(Float::class.javaObjectType)
    }

    @Test
    fun nextOf_createsInt() {
        val sut = FixtureFactory.create()

        val result = sut.next<Int>()

        assertThat(result)
            .isInstanceOf(Integer::class.javaObjectType)
    }

    @Test
    fun nextOf_createsLong() {
        val sut = FixtureFactory.create()

        val result = sut.next<Long>()

        assertThat(result)
            .isInstanceOf(Long::class.javaObjectType)
    }

    @Test
    fun nextOf_createsString() {
        val sut = FixtureFactory.create()

        val result = sut.next<String>()

        assertThat(result)
            .isInstanceOf(String::class.java)
    }

    @Test
    fun nextOf_createsClassWithObjectParam() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestClassWithObjectParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithObjectParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithInterfaceParam() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestClassWithInterfaceParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithInterfaceParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithSealedClassParam() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestClassWithSealedClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithSealedClassParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithClassParam() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestClassWithClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithClassParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithDataClassParam() {
        val sut = FixtureFactory.create()

        val result = sut.next<TestClassWithDataClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithDataClassParam::class.java)
    }

    @Test
    fun nextOf_throwsNoUsableConstructor() {
        val sut = FixtureFactory.create()

        var error: Throwable? = null
        try {
            sut.next<TestClassWithPrivateConstructor>()
        } catch (ex: Throwable) {
            error = ex
        }

        assertThat(error)
            .isInstanceOf(NoUsableConstructor::class.java)
    }
}