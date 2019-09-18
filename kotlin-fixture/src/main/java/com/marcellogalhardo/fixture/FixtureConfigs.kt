package com.marcellogalhardo.fixture

data class FixtureConfigs(
    val defaultRange: IntRange = 1..100,
    val longRange: LongRange = Int.MAX_VALUE..Long.MAX_VALUE,
    val charRange: CharRange = 'A'..'z',
    val mapRange: IntRange = 1..5,
    val listRange: IntRange = 1..5
)