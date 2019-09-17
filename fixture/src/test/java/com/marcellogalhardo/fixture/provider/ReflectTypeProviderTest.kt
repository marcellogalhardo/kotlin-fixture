package com.marcellogalhardo.fixture.provider

import com.google.common.truth.Truth.assertThat
import com.marcellogalhardo.fixture.*
import com.marcellogalhardo.fixture.TestClass
import com.marcellogalhardo.fixture.TestClassWithClassParam
import com.marcellogalhardo.fixture.TestClassWithDataClassParam
import com.marcellogalhardo.fixture.TestClassWithInterfaceParam
import com.marcellogalhardo.fixture.TestClassWithObjectParam
import com.marcellogalhardo.fixture.TestClassWithPrivateConstructor
import com.marcellogalhardo.fixture.TestClassWithSealedClassParam
import com.marcellogalhardo.fixture.TestDataClass
import com.marcellogalhardo.fixture.TestInterface
import com.marcellogalhardo.fixture.TestObject
import com.marcellogalhardo.fixture.TestSealedClass
import org.junit.Test

class ReflectTypeProviderTest {

    @Test
    fun nextOf_createsObjectType() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestObject>()

        assertThat(result)
            .isInstanceOf(TestObject::class.java)
    }

    @Test
    fun nextOf_createsInterface() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestInterface>()

        assertThat(result)
            .isInstanceOf(TestInterface::class.java)
    }

    @Test
    fun nextOf_createsSealedClassWithAtLeastOneSubClass() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestSealedClass>()

        assertThat(result)
            .isInstanceOf(TestSealedClass::class.java)
    }

    @Test
    fun nextOf_createsClass() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestClass>()

        assertThat(result)
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun nextOf_createsDataClass() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestDataClass>()

        assertThat(result)
            .isInstanceOf(TestDataClass::class.java)
    }

    @Test
    fun nextOf_createsClassWithObjectParam() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestClassWithObjectParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithObjectParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithInterfaceParam() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestClassWithInterfaceParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithInterfaceParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithSealedClassParam() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestClassWithSealedClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithSealedClassParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithClassParam() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestClassWithClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithClassParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithDataClassParam() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

        val result = sut.next<TestClassWithDataClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithDataClassParam::class.java)
    }

    @Test
    fun nextOf_throwsNoUsableConstructor() {
        val fixture = Fixture()
        val sut = ReflectTypeProvider(fixture::next, FixtureRandom())

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