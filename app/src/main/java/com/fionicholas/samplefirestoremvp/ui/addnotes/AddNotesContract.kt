package com.fionicholas.samplefirestoremvp.ui.addnotes

import com.fionicholas.samplefirestoremvp.data.model.Notes

interface AddNotesContract {
    interface View {
        fun successAddNotes()
        fun failedAddNotes(error: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        suspend fun addNotes(notes: Notes)
    }
}