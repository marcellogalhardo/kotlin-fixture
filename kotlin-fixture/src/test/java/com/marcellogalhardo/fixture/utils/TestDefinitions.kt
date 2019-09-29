package com.marcellogalhardo.fixture.utils

internal class TestClassWithObjectParam(val testParam: TestObject)

internal class TestClassWithInterfaceParam(val testParam: TestInterface)

internal class TestClassWithSealedClassParam(val testParam: TestSealedClassWithSubClasses)

internal class TestClassWithClassParam(val testParam: TestClass)

internal class TestClassWithDataClassParam(val testParam: TestDataClass)

internal object TestObject

internal interface TestInterface {
    fun echo(): String
}

internal abstract class TestAbstractClass

internal sealed class TestSealedClassWithSubClasses {
    object TestSubClass : TestSealedClassWithSubClasses()
}

internal sealed class TestSealedClassWithoutSubClasses

internal class TestClass

internal class TestClassWithGenerics<T> constructor(
    val testParam: T
)

internal class TestClassWithPrivateConstructor private constructor()

internal data class TestDataClass(val id: Int)