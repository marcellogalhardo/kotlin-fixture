package com.mgalhardo.fixture.generator

import java.util.*
import kotlin.random.Random

interface StringGenerator {

    fun string(): String

    fun string(prefix: String): String

    fun string(size: Int): String

    fun string(prefix: String, size: Int): String

    class Default(
        private val random: Random = Random.Default
    ) : StringGenerator {
        override fun string(): String {
            return UUID.randomUUID().toString()
        }

        override fun string(prefix: String): String = "$prefix-${string()}"

        override fun string(size: Int): String {
            val sb = StringBuilder(size)
            for (i in 0 until size) {
                sb.append(
                    ALLOWED_CHARACTERS[random.nextInt(
                        ALLOWED_CHARACTERS.length
                    )]
                )
            }
            return sb.toString()
        }

        override fun string(prefix: String, size: Int): String {
            return prefix + string(size - prefix.length)
        }

        companion object {
            private const val ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        }
    }
}