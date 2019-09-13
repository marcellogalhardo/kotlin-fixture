package com.marcellogalhardo.fixture.resolver

import com.marcellogalhardo.fixture.FixtureTypeResolver
import com.marcellogalhardo.fixture.NextFunction
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class SealedClassTypeResolver(
    private val nextFunction: NextFunction
) : FixtureTypeResolver {

    override fun resolve(classRef: KClass<*>, typeRef: KType): Any? {
        if (classRef.isSealed) {
            val sealedSubClass = classRef.sealedSubclasses.firstOrNull()
            if (sealedSubClass != null) {
                return nextFunction(sealedSubClass, typeRef)
            }
        }
        return null
    }
}