package com.marcellogalhardo.fixture

import kotlin.reflect.KClassifier

class AbstractClassNotSupportedException :
    IllegalArgumentException("Abstract classes are not supported.")

class ClassifierNotSupportedException(classifier: KClassifier?) :
    IllegalArgumentException("Type of the classifier $classifier is not supported")

class NoUsableConstructor : IllegalArgumentException()