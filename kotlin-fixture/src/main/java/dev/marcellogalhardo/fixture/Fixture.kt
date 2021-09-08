package dev.marcellogalhardo.fixture

sealed interface Fixture {

    fun <T> generate(block: Fixture.() -> T): T

    fun <K, V> map(
        generateKey: Fixture.(index: Int) -> K,
        generateValue: Fixture.(index: Int) -> V,
    ): Map<K, V>

    fun <K, V> map(
        until: Int,
        generateKey: Fixture.(index: Int) -> K,
        generateValue: Fixture.(index: Int) -> V,
    ): Map<K, V>

    fun <K, V> map(
        from: Int,
        until: Int,
        generateKey: Fixture.(index: Int) -> K,
        generateValue: Fixture.(index: Int) -> V,
    ): Map<K, V>

    fun <T> list(generate: Fixture.(index: Int) -> T): List<T>

    fun <T> list(
        until: Int,
        generate: Fixture.(index: Int) -> T,
    ): List<T>

    fun <T> list(
        from: Int,
        until: Int,
        generate: Fixture.(index: Int) -> T,
    ): List<T>

    fun boolean(): Boolean

    fun char(): Char

    fun char(range: CharRange): Char

    fun double(): Double

    fun double(until: Double): Double

    fun double(
        from: Double,
        until: Double
    ): Double

    fun float(): Float

    fun int(): Int

    fun int(until: Int): Int

    fun int(from: Int, until: Int): Int

    fun long(): Long

    fun long(until: Long): Long

    fun long(from: Long, until: Long): Long

    fun string(prefix: String? = null, suffix: String? = null): String

    companion object Default : Fixture by Fixture()
}
