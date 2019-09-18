package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.FixtureConfigs
import com.marcellogalhardo.fixture.FixtureContext
import com.marcellogalhardo.fixture.FixtureRandom
import com.marcellogalhardo.fixture.FixtureResolver

internal class ListTypeResolver(
    private val random: FixtureRandom,
    private val configs: FixtureConfigs,
    private val resolver: FixtureResolver
) : FixtureResolver.Type {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            List::class, Collection::class -> {
                val size = random.nextInt(configs.collectionRange)
                val paramType = classType.arguments[0].type!!

                return List(size) {
                    resolver.resolve(classRef, classType, paramType)
                }
            }
            else -> null
        }
    }
}