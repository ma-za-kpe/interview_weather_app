package com.maku.interviewweatherapp.common.data.api.models.mapper

interface ApiMapper<E, D> {

  fun mapToDomain(apiEntity: E): D
}