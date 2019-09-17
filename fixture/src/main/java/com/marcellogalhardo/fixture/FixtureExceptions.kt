package com.marcellogalhardo.fixture

import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.jvm.jvmName

open class FixtureException(message: String) : Error(message)

class AbstractClassNotSupportedException : FixtureException("Abstract classes are not supported")

class TypeNotSupportedException(classRef: KClass<*>) :
    FixtureException("Type ${classRef.jvmName} is not supported")

class ClassifierNotSupportedException(classifier: KClassifier?) :
    FixtureException("Type of the classifier $classifier is not supported")

class NoUsableConstructor : FixtureException("No usable constructor")

class SealedClassWithoutSubClassesException :
    FixtureException("Sealed class without subclasses are not supported")