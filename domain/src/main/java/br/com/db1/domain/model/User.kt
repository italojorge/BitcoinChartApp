package br.com.db1.domain.model

data class User(
    val name: String,
    var phone: String,
    var password: String,
    var verificationCode: String
)

