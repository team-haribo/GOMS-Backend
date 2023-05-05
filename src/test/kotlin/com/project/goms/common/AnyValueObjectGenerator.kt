package com.project.goms.common

import com.project.goms.domain.account.usecase.dto.ProfileDto
import com.project.goms.domain.account.entity.Authority
import com.project.goms.domain.account.usecase.dto.AccountDto
import com.project.goms.domain.late.usecase.dto.LateRankDto
import com.project.goms.domain.outing.usecase.dto.OutingAccountDto
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

object AnyValueObjectGenerator {

    inline fun <reified T : Any> anyValueObject(vararg pairs: Pair<String, Any>): T {
        val parameterMap = mutableMapOf(*pairs)
        val constructor = T::class.primaryConstructor!!

        val params = constructor.parameters.map {
            val cls = it.type.classifier as KClass<*>
            if (parameterMap[it.name] != null) parameterMap.remove(it.name)
            else anyValue(cls)
        }
        require(parameterMap.isEmpty())

        return constructor.call(*params.toTypedArray())
    }

    fun anyValue(cls: KClass<*>): Any? =
        when (cls) {
            Boolean::class -> false
            Byte::class -> 0.toByte()
            Short::class -> 0.toShort()
            Char::class -> 0.toChar()
            Int::class -> 0
            Long::class -> 0L
            Float::class -> 0.0F
            Double::class -> 0.0
            String::class -> ""
            Date::class -> Date(0)
            UUID::class -> UUID.randomUUID()

            BooleanArray::class -> BooleanArray(0)
            ByteArray::class -> ByteArray(0)
            CharArray::class -> CharArray(0)
            IntArray::class -> IntArray(0)
            LongArray::class -> LongArray(0)
            DoubleArray::class -> DoubleArray(0)

            List::class -> List<Any>(0) {}
            Map::class -> HashMap<Any, Any>()
            Set::class -> HashSet<Any>()
            ArrayList::class -> ArrayList<Any>()
            HashMap::class -> HashMap<Any, Any>()
            HashSet::class -> HashSet<Any>()
            Authority::class -> Authority.ROLE_STUDENT
            ZonedDateTime::class -> ZonedDateTime.now()

            ProfileDto.StudentNum::class -> ProfileDto.StudentNum(0, 0, 0)
            LateRankDto.StudentNum::class -> LateRankDto.StudentNum(0, 0, 0)
            OutingAccountDto.StudentNum::class -> OutingAccountDto.StudentNum(0, 0, 0)
            AccountDto.StudentNum::class -> AccountDto.StudentNum(0, 0, 0)

            else -> {
                throw IllegalArgumentException(
                    "Fields of type ${cls.qualifiedName} cannot automatically generate values"
                )
            }
        }

}