package com.applift.data.error.mapper

interface ErrorMapperInterface {
    fun getErrorString(errorId: Int): String
    val errorsMap: Map<Int, String>
}