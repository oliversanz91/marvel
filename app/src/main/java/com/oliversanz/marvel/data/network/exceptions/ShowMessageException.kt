package com.oliversanz.marvel.data.network.exceptions

import java.io.IOException

class ShowMessageException(
    val title: String? = null,
    override val message: String? = "Error genérico"
): IOException(message)