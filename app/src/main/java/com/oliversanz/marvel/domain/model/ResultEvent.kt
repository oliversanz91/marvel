package com.oliversanz.marvel.domain.model

sealed class ResultEvent {
    object Success: ResultEvent()
    class Error(val throwable: Throwable): ResultEvent()
}