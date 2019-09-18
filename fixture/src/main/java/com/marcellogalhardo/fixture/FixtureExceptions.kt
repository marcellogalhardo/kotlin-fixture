package com.marcellogalhardo.fixture

open class FixtureException(message: String?) : Error(message)

class ContextNotSupported(context: FixtureContext) :
    FixtureException("$context is not supported")

class AbstractClassNotSupportedException : FixtureException("Abstract classes are not supported")

class ListCreationFailedException(message: String?) :
    FixtureException("List creation failed: $message")

class MapCreationFailedException(message: String?) :
    FixtureException("Map creation failed: $message")

class SealedClassWithoutSubClassesException :
    FixtureException("Sealed class without subclasses are not supported")