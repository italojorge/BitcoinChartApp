package br.com.db1.data_local.mapper

interface Mapper<E, D> {
    fun toDomain(entity: E): D
    fun fromDomain(domain: D): E
}
