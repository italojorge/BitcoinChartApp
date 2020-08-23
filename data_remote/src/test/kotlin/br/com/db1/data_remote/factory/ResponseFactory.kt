package br.com.db1.data_remote.factory

interface ResponseFactory<T> {

    fun dumbInstance(): T

    fun makeDumbList(size: Int = 5) = mutableListOf<T>().apply {
        repeat(size) {
            add(dumbInstance())
        }
    }.toList()
}