package com.fionicholas.samplefirestoremvp.data

import com.fionicholas.samplefirestoremvp.data.model.Notes
import com.fionicholas.samplefirestoremvp.utils.BaseResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class NotesRepository : NotesDataSource {

    private val notesCollection =
        FirebaseFirestore.getInstance().collection("notes")

    override fun getNotes() = flow<BaseResult<List<Notes>>> {
        emit(BaseResult.loading())

        val snapshot = notesCollection.get().await()
        val notes = snapshot.toObjects(Notes::class.java)
        emit(BaseResult.success(notes))

    }.catch {
        emit(BaseResult.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    override fun addNotes(notes: Notes) = flow<BaseResult<DocumentReference>> {

        emit(BaseResult.loading())

        val notesRef = notesCollection.add(notes).await()

        emit(BaseResult.success(notesRef))

    }.catch {
        emit(BaseResult.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}