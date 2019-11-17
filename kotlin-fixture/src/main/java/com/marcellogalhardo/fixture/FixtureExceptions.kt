package com.marcellogalhardo.fixture

import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

sealed class FixtureException(message: String?) : Error(message)

class TypeNotSupported(classRef: KClass<*>) :
    FixtureException("${classRef.jvmName} is not supported")

class AbstractClassNotSupportedException : FixtureException("Abstract classes are not supported")

class SealedClassWithoutSubClassesException :
    FixtureException("Sealed class without subclasses are not supported")