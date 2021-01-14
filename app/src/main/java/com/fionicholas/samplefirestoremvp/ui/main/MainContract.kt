package com.fionicholas.samplefirestoremvp.ui.main

import com.fionicholas.samplefirestoremvp.data.model.Notes

interface MainContract {
    interface View {
        fun showNotes(data: List<Notes>)
        fun showLoading()
        fun hideLoading()
        fun failed(error : String)
    }

    interface Presenter {
        suspend fun getAllNotes()
    }
}