package com.mgalhardo.fixture.generator

interface TypeGenerator<T : Any> {

    fun register(provider: () -> T)

    fun register(key: String, provider: () -> T)

    fun create(): T

    fun create(key: String): T

    class Default<T : Any> : TypeGenerator<T> {

        private val providerMap = hashMapOf<String, () -> T>()

        override fun register(provider: () -> T) {
            providerMap[""] = provider
        }

        override fun register(key: String, provider: () -> T) {
            providerMap[key] = provider
        }

        override fun create(): T {
            return create("")
        }

        override fun create(key: String): T {
            return providerMap[key]!!.invoke()
        }

    }

}