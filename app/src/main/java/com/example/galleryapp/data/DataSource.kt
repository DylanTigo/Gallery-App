package com.example.galleryapp.data

import com.example.galleryapp.model.GalleryItem
import com.example.galleryapp.R

class DataSource () {
    fun loadGalleryItems(): List<GalleryItem> {
        return listOf<GalleryItem>(
            GalleryItem(R.string.madrid, R.string.description1, R.drawable.madrid, "2025"),
            GalleryItem(R.string.arsenal, R.string.description2, R.drawable.arsenal, "2024"),
            GalleryItem(R.string.barcelona, R.string.description3, R.drawable.barcelona, "2025"),
            GalleryItem(R.string.bayern, R.string.description4, R.drawable.bayern, "2024"),
            GalleryItem(R.string.arsenal, R.string.description5, R.drawable.arsenal, "2024"),
            GalleryItem(R.string.inter, R.string.description6, R.drawable.inter, "2025")
        )
    }

}