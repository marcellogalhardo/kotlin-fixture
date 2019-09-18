package com.marcellogalhardo.fixture.resolver.type

import com.marcellogalhardo.fixture.*

internal class ListTypeResolver(
    private val random: FixtureRandom,
    private val configs: FixtureConfigs,
    private val resolver: FixtureResolver
) : FixtureResolver.Type {

    override fun resolveType(context: FixtureContext.Type): Any? = context.run {
        return when (classRef) {
            List::class, Collection::class -> try {
                val size = random.nextInt(configs.listRange)
                val paramType = classType.arguments[0].type!!

                return List(size) {
                    resolver.resolve(classRef, classType, paramType)
                }
            } catch (error: Throwable) {
                throw ListCreationFailedException(error.message)
            }
            else -> null
        }
    }
}