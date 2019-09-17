package com.marcellogalhardo.fixture

import com.marcellogalhardo.fixture.resolver.FixtureParamResolver
import com.marcellogalhardo.fixture.resolver.FixtureTypeResolver

class FixtureResolver(
    private val nextFunction: NextFunction,
    private val configs: FixtureConfigs,
    private val random: FixtureRandom,
    private val paramResolver: FixtureParamResolver = FixtureParamResolver(nextFunction),
    private val typeResolver: FixtureTypeResolver = FixtureTypeResolver(
        configs,
        random,
        paramResolver,
        nextFunction
    )
) : FixtureTypeResolver by typeResolver, FixtureParamResolver by paramResolver