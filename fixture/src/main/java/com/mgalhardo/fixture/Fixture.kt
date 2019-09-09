package com.mgalhardo.fixture

import com.mgalhardo.fixture.provider.CustomTypeProvider
import com.mgalhardo.fixture.provider.ReflectTypeProvider
import com.mgalhardo.fixture.provider.StandardTypeProvider
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmName

@Suppress("UNCHECKED_CAST")
class Fixture(
    private val standardTypeProvider: StandardTypeProvider = StandardTypeProvider.Default()
) : StandardTypeProvider by standardTypeProvider {

    val reflectTypeProvider: ReflectTypeProvider = ReflectTypeProvider(this)

    val customTypeProvider = hashMapOf<String, CustomTypeProvider<out Any>>()

    inline fun <reified T : Any> register(noinline providerBlock: (Fixture) -> T) {
        val provider = customTypeProvider.getOrPut(T::class.jvmName) {
            CustomTypeProvider.Default<T>()
        } as CustomTypeProvider<T>
        provider.register {
            providerBlock(this)
        }
    }

    inline fun <reified T : Any> register(key: String, noinline providerBlock: (Fixture) -> T) {
        val provider = customTypeProvider.getOrPut(T::class.jvmName) {
            CustomTypeProvider.Default<T>()
        } as CustomTypeProvider<T>
        provider.register(key) {
            providerBlock(this)
        }
    }

    internal fun reflectNextOf(classRef: KClass<*>, type: KType): Any? {
        val typeGenerator = customTypeProvider[classRef.jvmName]
        if (typeGenerator != null) {
            return typeGenerator.nextOf()
        }
        return reflectTypeProvider.nextRandomInstance(classRef, type)
    }

    inline fun <reified T : Any> nextOf(): T {
        val typeGenerator = customTypeProvider[T::class.jvmName]
        if (typeGenerator != null) {
            return typeGenerator.nextOf() as T
        }
        return reflectTypeProvider.nextOf()
    }

    inline fun <reified T : Any> nextOf(key: String): T {
        return customTypeProvider[T::class.jvmName]!!.nextOf(key) as T
    }
}

inline fun <reified T : Any> Fixture.nextListOf(size: Int): List<T> = List(size) {
    nextOf<T>()
}