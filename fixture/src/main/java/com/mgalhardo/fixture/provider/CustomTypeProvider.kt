package com.mgalhardo.fixture.provider

interface CustomTypeProvider<T : Any> {

    fun register(provider: () -> T)

    fun register(key: String, provider: () -> T)

    fun nextOf(): T

    fun nextOf(key: String): T

    class Default<T : Any> : CustomTypeProvider<T> {

        private val providerMap = hashMapOf<String, () -> T>()

        override fun register(provider: () -> T) {
            providerMap[""] = provider
        }

        override fun register(key: String, provider: () -> T) {
            providerMap[key] = provider
        }

        override fun nextOf(): T {
            return nextOf("")
        }

        override fun nextOf(key: String): T {
            return providerMap[key]!!.invoke()
        }

    }

}