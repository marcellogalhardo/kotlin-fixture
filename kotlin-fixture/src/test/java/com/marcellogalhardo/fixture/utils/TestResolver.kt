package com.marcellogalhardo.fixture.utils

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver

class TestResolver : FixtureResolver {

    var context: FixtureContext? = null
    var result: Any? = Any()

    override fun resolve(creator: FixtureCreator, context: FixtureContext): Any? {
        this.context = context
        return result
    }

}