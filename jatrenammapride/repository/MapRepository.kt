package com.example.jatrenammapride.repository

import com.example.jatrenammapride.model.MapMarker
import com.google.firebase.firestore.FirebaseFirestore

class MapRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun addMarker(marker: MapMarker) {

        firestore.collection("map_markers")
            .document(marker.id)
            .set(marker)
    }
}