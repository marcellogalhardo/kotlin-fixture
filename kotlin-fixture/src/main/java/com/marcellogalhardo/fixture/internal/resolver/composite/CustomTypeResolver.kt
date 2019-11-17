package com.marcellogalhardo.fixture.internal.resolver.composite

import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureCreator
import com.marcellogalhardo.fixture.FixtureResolver
import kotlin.reflect.KClass

class CustomTypeResolver(
    private val dataSet: HashMap<KClass<*>, FixtureResolver> = hashMapOf()
) : FixtureResolver, MutableMap<KClass<*>, FixtureResolver> by dataSet {

    override fun resolve(creator: FixtureCreator, context: FixtureContext): Any? {
        return dataSet[context.classRef]?.resolve(creator, context)
    }
}