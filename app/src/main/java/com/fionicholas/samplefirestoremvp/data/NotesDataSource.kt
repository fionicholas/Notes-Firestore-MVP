package com.fionicholas.samplefirestoremvp.data

import com.fionicholas.samplefirestoremvp.data.model.Notes
import com.fionicholas.samplefirestoremvp.utils.BaseResult
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow

interface NotesDataSource {

    fun getNotes() : Flow<BaseResult<List<Notes>>>

    fun addNotes(notes: Notes) : Flow<BaseResult<DocumentReference>>
}