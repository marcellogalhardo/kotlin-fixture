package com.marcellogalhardo.fixture

import java.util.*

fun <T> linkedListOf(vararg elements: T): LinkedList<T> = if (elements.isNotEmpty()) {
    LinkedList(elements.asList())
} else {
    LinkedList()
}