package com.project.gamedb.ultis

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.project.gamedb.R

class Dialogs : DialogFragment() {
    private var inform = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(inform)
                .create()
        } ?: throw IllegalStateException(getString(R.string.err_no_activiy))
    }

    fun setInform(inform: String) {
        this.inform = inform
    }
}
