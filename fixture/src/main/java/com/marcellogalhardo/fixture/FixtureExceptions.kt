package com.marcellogalhardo.fixture

open class FixtureException(message: String) : Error(message)

class AbstractClassNotSupportedException : FixtureException("Abstract classes are not supported")


class ContextNotSupported(context: FixtureContext) :
    FixtureException("$context is not supported")

class SealedClassWithoutSubClassesException :
    FixtureException("Sealed class without subclasses are not supported")