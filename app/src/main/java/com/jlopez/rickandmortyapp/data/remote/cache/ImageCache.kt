package com.jlopez.rickandmortyapp.data.remote.cache

import android.graphics.Bitmap
import javax.inject.Inject

class ImageCache  @Inject constructor() {
    private val cache = mutableMapOf<String, Bitmap>()

    fun getImage(url: String): Bitmap? {
        return cache[url]
    }

    fun saveImage(url: String, image: Bitmap) {
        cache[url] = image
    }
}