package com.mgalhardo.fixture.generator

import kotlin.random.Random

interface LongGenerator {

    fun long(): Long

    fun long(until: Long): Long

    fun long(from: Long, until: Long): Long

    class Default(
        private val random: Random = Random.Default
    ) : LongGenerator {

        override fun long(): Long = random.nextLong()

        override fun long(until: Long): Long = random.nextLong(until)

        override fun long(from: Long, until: Long): Long = random.nextLong(from, until)
    }
}