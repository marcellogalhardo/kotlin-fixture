package com.marcellogalhardo.fixture

sealed class FixtureException(message: String?) : Error(message)

class ContextNotSupported(context: FixtureContext) :
    FixtureException("$context is not supported")

class AbstractClassNotSupportedException : FixtureException("Abstract classes are not supported")

class SealedClassWithoutSubClassesException :
    FixtureException("Sealed class without subclasses are not supported")