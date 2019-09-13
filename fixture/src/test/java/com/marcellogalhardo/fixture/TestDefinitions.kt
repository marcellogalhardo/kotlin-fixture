package com.marcellogalhardo.fixture

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