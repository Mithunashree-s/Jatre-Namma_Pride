package com.example.jatrenammapride.repository

import com.example.jatrenammapride.model.Event
import com.google.firebase.firestore.FirebaseFirestore

class ScheduleRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun addEvent(event: Event) {

        firestore.collection("schedule")
            .document(event.id)
            .set(event)
    }
}