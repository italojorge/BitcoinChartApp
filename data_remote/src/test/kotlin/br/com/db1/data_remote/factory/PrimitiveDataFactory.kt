package br.com.db1.data_remote.factory

import java.util.*

object PrimitiveDataFactory {

    fun makeDumbString() = UUID.randomUUID().toString()

    fun makeDumbStringList(size: Int) = mutableListOf<String>().apply {
        repeat(size) {
            add(makeDumbString())
        }
    }.toList()

    fun makeDumbInt() = Random().nextInt()

    fun makeDumbLong() = Random().nextLong()

    fun makeDumbDouble() = Random().nextDouble()
}