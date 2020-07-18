package com.applift.data.error

interface ErrorFactory {
    fun getError(errorCode: Int): Error
}