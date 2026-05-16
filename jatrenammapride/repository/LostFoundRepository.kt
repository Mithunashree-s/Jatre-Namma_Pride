package com.example.jatrenammapride.repository

import com.example.jatrenammapride.model.LostFoundItem
import com.google.firebase.firestore.FirebaseFirestore

class LostFoundRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun addItem(item: LostFoundItem) {

        firestore.collection("lost_found")
            .document(item.id)
            .set(item)
    }
}