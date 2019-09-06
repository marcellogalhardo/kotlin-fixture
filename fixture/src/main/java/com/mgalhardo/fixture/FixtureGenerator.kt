package com.mgalhardo.fixture

import com.mgalhardo.fixture.generator.*

interface FixtureGenerator :
    BitsGenerator,
    BooleanGenerator,
    BytesGenerator,
    DoubleGenerator,
    FloatGenerator,
    IntGenerator,
    LongGenerator,
    StringGenerator {

    class Default(
        private val bitsGenerator: BitsGenerator = BitsGenerator.Default(),
        private val booleanGenerator: BooleanGenerator = BooleanGenerator.Default(),
        private val bytesGenerator: BytesGenerator = BytesGenerator.Default(),
        private val doubleGenerator: DoubleGenerator = DoubleGenerator.Default(),
        private val floatGenerator: FloatGenerator = FloatGenerator.Default(),
        private val intGenerator: IntGenerator = IntGenerator.Default(),
        private val longGenerator: LongGenerator = LongGenerator.Default(),
        private val stringGenerator: StringGenerator = StringGenerator.Default()
    ) : FixtureGenerator,
        BitsGenerator by bitsGenerator,
        BooleanGenerator by booleanGenerator,
        BytesGenerator by bytesGenerator,
        DoubleGenerator by doubleGenerator,
        FloatGenerator by floatGenerator,
        IntGenerator by intGenerator,
        LongGenerator by longGenerator,
        StringGenerator by stringGenerator
}