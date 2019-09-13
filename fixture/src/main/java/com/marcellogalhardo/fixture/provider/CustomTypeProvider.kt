package com.marcellogalhardo.fixture.provider

interface CustomTypeProvider<T : Any> {

    fun register(key: String, provider: () -> T)

    fun nextOf(key: String): T

    class Default<T : Any> : CustomTypeProvider<T> {

        private val providerMap = hashMapOf<String, () -> T>()

        override fun register(key: String, provider: () -> T) {
            providerMap[key] = provider
        }

        override fun nextOf(key: String): T {
            return providerMap[key]!!.invoke()
        }
    }
}