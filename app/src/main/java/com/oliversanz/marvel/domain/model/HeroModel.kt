package com.oliversanz.marvel.domain.model


data class HeroModel(
    val id: String,
    val name: String,
    val description: String,
    val image: ImageModel,
    val url: List<UrlModel>
) {

    fun getDescriptionText(): String {
        return if (description.isBlank()) "Descripción no disponible"
        else description
    }

}
