package br.com.db1.presentation.mapper

interface PresentationMapper<P, D> {

    fun toDomain(presentation: P): D

    fun fromDomain(domain: D): P

}