package com.fionicholas.samplefirestoremvp.ui.addnotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fionicholas.samplefirestoremvp.R
import com.fionicholas.samplefirestoremvp.data.model.Notes
import com.fionicholas.samplefirestoremvp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddNotesActivity : AppCompatActivity(), AddNotesContract.View {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AddNotesActivity::class.java))
        }
    }

    private lateinit var presenter: AddNotesPresenter

    private lateinit var dialog: AlertDialog

    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null) {
            supportActionBar?.title = getString(R.string.action_add_notes)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(false)

        builder.setView(R.layout.layout_loading_dialog)
        dialog = builder.create()

        presenter = AddNotesPresenter(this)

        btnAddNotes.setOnClickListener {
            uiScope.launch {
                addNotes()
            }
        }
    }

    private suspend fun addNotes() {
        val title = edtTitle.text.toString().trim()
        val message = edtMessage.text.toString().trim()
        val id = UUID.randomUUID().toString()
        val notes = Notes(id, title, message)
        presenter.addNotes(notes)
    }

    override fun successAddNotes() {
        MainActivity.start(this)
    }

    override fun failedAddNotes(error: String) {
        Toast.makeText(this, "Failed to Send Data!", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.dismiss()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}