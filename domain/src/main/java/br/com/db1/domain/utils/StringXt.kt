package br.com.db1.domain.utils

fun String.unmask() = this.replace("[^\\d]".toRegex(), "")
