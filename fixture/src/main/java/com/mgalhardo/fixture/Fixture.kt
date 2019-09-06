package com.mgalhardo.fixture

import com.mgalhardo.fixture.generator.ReflectGenerator
import com.mgalhardo.fixture.generator.TypeGenerator
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.jvm.jvmName

@Suppress("UNCHECKED_CAST")
class Fixture(
    val generator: FixtureGenerator = FixtureGenerator.Default()
) : FixtureGenerator by generator {

    val reflector: ReflectGenerator = ReflectGenerator(this)

    val generatorMap = hashMapOf<String, TypeGenerator<out Any>>()

    inline fun <reified T : Any> registerMany(noinline generatorBlock: TypeGenerator<T>.() -> Unit) {
        val generator = generatorMap.getOrPut(T::class.jvmName) {
            TypeGenerator.Default<T>()
        } as TypeGenerator<T>
        generator.apply(generatorBlock)
        generatorMap[T::class.jvmName] = generator
    }

    inline fun <reified T : Any> register(noinline provider: () -> T) {
        val generator = generatorMap.getOrPut(T::class.jvmName) {
            TypeGenerator.Default<T>()
        } as TypeGenerator<T>
        generator.register(provider)
    }

    inline fun <reified T : Any> register(key: String, noinline provider: () -> T) {
        val generator = generatorMap.getOrPut(T::class.jvmName) {
            TypeGenerator.Default<T>()
        } as TypeGenerator<T>
        generator.register(key, provider)
    }

    internal fun make(classRef: KClass<*>, type: KType): Any? {
        val typeGenerator = generatorMap[classRef.jvmName]
        if (typeGenerator != null) {
            return typeGenerator.create()
        }
        return reflector.makeRandomInstance(classRef, type)
    }

    inline fun <reified T : Any> create(): T {
        val typeGenerator = generatorMap[T::class.jvmName]
        if (typeGenerator != null) {
            return typeGenerator.create() as T
        }
        return reflector.create()
    }

    inline fun <reified T : Any> createListOf(size: Int): List<T> = List(size) {
        create<T>()
    }

    inline fun <reified T : Any> create(key: String): T {
        return generatorMap[T::class.jvmName]!!.create(key) as T
    }

    inline fun <reified T : Any> generatorOf(): TypeGenerator<T> {
        return generatorMap[T::class.jvmName] as TypeGenerator<T>
    }
}