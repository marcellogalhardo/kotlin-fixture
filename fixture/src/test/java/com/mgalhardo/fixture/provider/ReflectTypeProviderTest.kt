package com.mgalhardo.fixture.provider

import com.google.common.truth.Truth.assertThat
import com.mgalhardo.fixture.*
import com.mgalhardo.fixture.TestClass
import com.mgalhardo.fixture.TestClassWithClassParam
import com.mgalhardo.fixture.TestClassWithDataClassParam
import com.mgalhardo.fixture.TestClassWithInterfaceParam
import com.mgalhardo.fixture.TestClassWithObjectParam
import com.mgalhardo.fixture.TestClassWithPrivateConstructor
import com.mgalhardo.fixture.TestClassWithSealedClassParam
import com.mgalhardo.fixture.TestDataClass
import com.mgalhardo.fixture.TestInterface
import com.mgalhardo.fixture.TestObject
import com.mgalhardo.fixture.TestSealedClass
import org.junit.Test

class ReflectTypeProviderTest {

    @Test
    fun nextOf_createsObjectType() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestObject>()

        assertThat(result)
            .isInstanceOf(TestObject::class.java)
    }

    @Test
    fun nextOf_createsInterface() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestInterface>()

        assertThat(result)
            .isInstanceOf(TestInterface::class.java)
    }

    @Test
    fun nextOf_createsSealedClassWithAtLeastOneSubClass() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestSealedClass>()

        assertThat(result)
            .isInstanceOf(TestSealedClass::class.java)
    }

    @Test
    fun nextOf_createsClass() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestClass>()

        assertThat(result)
            .isInstanceOf(TestClass::class.java)
    }

    @Test
    fun nextOf_createsDataClass() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestDataClass>()

        assertThat(result)
            .isInstanceOf(TestDataClass::class.java)
    }

    @Test
    fun nextOf_createsClassWithObjectParam() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestClassWithObjectParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithObjectParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithInterfaceParam() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestClassWithInterfaceParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithInterfaceParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithSealedClassParam() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestClassWithSealedClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithSealedClassParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithClassParam() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestClassWithClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithClassParam::class.java)
    }

    @Test
    fun nextOf_createsClassWithDataClassParam() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

        val result = sut.nextOf<TestClassWithDataClassParam>()

        assertThat(result)
            .isInstanceOf(TestClassWithDataClassParam::class.java)
    }

    @Test
    fun nextOf_throwsNoUsableConstructor() {
        val sut = ReflectTypeProvider(Fixture()::reflectNextOf, StandardTypeProvider.Default())

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