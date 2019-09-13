package com.marcellogalhardo.fixture

import kotlin.reflect.KClassifier

open class FixtureException(message: String) : Error(message)

class AbstractClassNotSupportedException : FixtureException("Abstract classes are not supported")

class ClassifierNotSupportedException(classifier: KClassifier?) :
    FixtureException("Type of the classifier $classifier is not supported")

class NoUsableConstructor : FixtureException("No usable constructor")