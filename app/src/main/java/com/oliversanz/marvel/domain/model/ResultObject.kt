package com.oliversanz.marvel.domain.model

sealed class ResultObject<T> {
    class Success<T>(val data : T): ResultObject<T>()
    class Error<T>(val throwable: Throwable): ResultObject<T>()
}