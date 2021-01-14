package com.fionicholas.samplefirestoremvp.ui.addnotes

import com.fionicholas.samplefirestoremvp.data.NotesRepository
import com.fionicholas.samplefirestoremvp.data.model.Notes
import com.fionicholas.samplefirestoremvp.utils.BaseResult
import kotlinx.coroutines.flow.collect

class AddNotesPresenter(private val view: AddNotesContract.View) : AddNotesContract.Presenter {

    private var repository: NotesRepository = NotesRepository()

    override suspend fun addNotes(notes: Notes) {
        repository.addNotes(notes).collect { state ->
            when (state) {
                is BaseResult.Loading -> {
                    view.showLoading()
                }

                is BaseResult.Success -> {
                    view.hideLoading()
                    view.successAddNotes()
                }

                is BaseResult.Failed -> {
                    view.hideLoading()
                    view.failedAddNotes(state.message)
                }
            }
        }
    }
}