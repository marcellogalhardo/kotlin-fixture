package dev.marcellogalhardo.fixture

import java.util.*
import kotlin.random.Random

fun Fixture(random: Random = Random.Default): Fixture {
    return FixtureImplementation(random)
}

internal class FixtureImplementation(
    private val random: Random,
) : Fixture {

    override fun <T> generator(generate: Fixture.() -> T): FixtureGenerator<T> {
        return FixtureGenerator(this, generate)
    }

    override fun <T> generate(block: Fixture.() -> T): T {
        return block(this)
    }

    override fun <K, V> map(until: Int, generate: Fixture.(index: Int) -> Pair<K, V>): Map<K, V> {
        return mutableMapOf<K, V>().apply {
            repeat(int(until)) { index ->
                val (key, value) = generate(this@FixtureImplementation, index)
                put(key, value)
            }
        }
    }

    override fun <K, V> map(
        from: Int,
        until: Int,
        generate: Fixture.(index: Int) -> Pair<K, V>
    ): Map<K, V> {
        return mutableMapOf<K, V>().apply {
            repeat(int(from, until)) { index ->
                val (key, value) = generate(this@FixtureImplementation, index)
                put(key, value)
            }
        }
    }

    override fun <T> list(until: Int, generate: Fixture.(index: Int) -> T): List<T> {
        return MutableList(int(until)) { index -> generate(this, index) }
    }

    override fun <T> list(from: Int, until: Int, generate: Fixture.(index: Int) -> T): List<T> {
        return MutableList(int(from, until)) { index -> generate(this, index) }
    }

    override fun boolean(): Boolean = random.nextBoolean()

    override fun char(): Char = ('A'..'z').random(random)

    override fun char(range: CharRange): Char = range.random(random)

    override fun double(): Double = random.nextDouble()

    override fun double(until: Double): Double = random.nextDouble(until)

    override fun double(
        from: Double,
        until: Double
    ): Double = random.nextDouble(from, until)

    override fun float(): Float = random.nextFloat()

    override fun int(): Int = random.nextInt()

    override fun int(until: Int): Int = random.nextInt(until)

    override fun int(from: Int, until: Int): Int = random.nextInt(from, until)

    override fun long(): Long = random.nextLong()

    override fun long(until: Long): Long = random.nextLong(until)

    override fun long(from: Long, until: Long): Long = random.nextLong(from, until)

    override fun string(prefix: String?, suffix: String?): String = buildString {
        prefix?.let { prefix -> append("$prefix-") }
        append(UUID.randomUUID().toString())
        suffix?.let { suffix -> append("-$suffix") }
    }
}
