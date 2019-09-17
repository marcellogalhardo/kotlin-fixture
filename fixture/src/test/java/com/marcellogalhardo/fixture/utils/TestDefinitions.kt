package com.marcellogalhardo.fixture.utils

internal class TestClassWithObjectParam(private val testParam: TestObject)

internal class TestClassWithInterfaceParam(private val testParam: TestInterface)

internal class TestClassWithSealedClassParam(private val testParam: TestSealedClassWithSubClasses)

internal class TestClassWithClassParam(private val testParam: TestClass)

internal class TestClassWithDataClassParam(private val testParam: TestDataClass)

internal object TestObject

internal interface TestInterface

internal abstract class TestAbstractClass

internal sealed class TestSealedClassWithSubClasses {
    object TestSubClass : TestSealedClassWithSubClasses()
}

internal sealed class TestSealedClassWithoutSubClasses

internal class TestClass

internal class TestClassWithPrivateConstructor private constructor()

internal data class TestDataClass(private val id: Int)