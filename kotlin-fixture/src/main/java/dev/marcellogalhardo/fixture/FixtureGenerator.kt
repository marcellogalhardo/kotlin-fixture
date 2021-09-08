package dev.marcellogalhardo.fixture

class FixtureGenerator<T> internal constructor(
    private val fixture: Fixture,
    private val generate: Fixture.() -> T
) {

    fun generate(block: (Fixture.(T) -> T)? = null): T {
        val generated = generate(fixture)
        return if (block == null) generated else block(fixture, generated)
    }
}
