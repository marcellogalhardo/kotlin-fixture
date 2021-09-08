package dev.marcellogalhardo.fixture

class FixtureGenerator<T> internal constructor(
    private val fixture: Fixture,
    private val generate: Fixture.() -> T
) {

    fun generate(): T = generate(fixture)
}