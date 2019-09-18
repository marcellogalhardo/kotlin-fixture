package com.marcellogalhardo.fixture

interface Fixture : FixtureRegistry, FixtureBuilder

@Suppress("FunctionName")
fun Fixture(
    configs: FixtureConfigs = FixtureConfigs()
): Fixture = DefaultFixture(configs)

@Suppress("FunctionName")
fun Fixture(
    configs: FixtureConfigs = FixtureConfigs(),
    apply: Fixture.() -> Unit
): Fixture = Fixture(configs).apply(apply)