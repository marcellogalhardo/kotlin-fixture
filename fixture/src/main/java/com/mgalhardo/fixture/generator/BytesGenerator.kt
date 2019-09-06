package com.mgalhardo.fixture.generator

import kotlin.random.Random

interface BytesGenerator {

    fun bytes(
        array: ByteArray,
        fromIndex: Int = 0,
        toIndex: Int = array.size
    ): ByteArray

    fun bytes(array: ByteArray): ByteArray

    fun bytes(size: Int): ByteArray

    class Default(
        private val random: Random = Random.Default
    ) : BytesGenerator {
        override fun bytes(array: ByteArray, fromIndex: Int, toIndex: Int): ByteArray =
            random.nextBytes(array, fromIndex, toIndex)

        override fun bytes(array: ByteArray): ByteArray = random.nextBytes(array)

        override fun bytes(size: Int): ByteArray = random.nextBytes(size)

    }

}