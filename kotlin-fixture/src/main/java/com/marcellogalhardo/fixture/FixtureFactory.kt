package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.internal.DefaultFixture
import com.marcellogalhardo.fixture.internal.DefaultRandom
import kotlin.random.Random

fun fixtureRandomOf(random: Random = Random.Default): FixtureRandom = DefaultRandom(random)

fun fixtureOf(
    configs: FixtureConfigs = FixtureConfigs()
): Fixture = DefaultFixture(configs)

fun fixtureOf(
    configs: FixtureConfigs = FixtureConfigs(),
    apply: Fixture.() -> Unit
): Fixture = fixtureOf(configs).apply(apply)