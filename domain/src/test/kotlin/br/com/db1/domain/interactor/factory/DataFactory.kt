package br.com.db1.domain.interactor.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

interface DataFactory<T> {

    fun makeData(): T

    fun makeList(count: Int): List<T> {
        val list = mutableListOf<T>()
        repeat(count) { list.add(makeData()) }
        return list
    }

    companion object {

        fun randomString() = UUID.randomUUID().toString()

        fun randomBoolean() = Math.random() < 0.5

        fun randomInt() = ThreadLocalRandom.current().nextInt(0, 1000 + 1)

        fun randomLong() = randomInt().toLong()

        fun randomFloat() = ThreadLocalRandom.current().nextLong()

        fun randomDouble() = ThreadLocalRandom.current().nextDouble()

        fun randomDateAsString() = Date(randomLong()).toString()

        fun randomDate() = Date(randomLong())
    }

}
