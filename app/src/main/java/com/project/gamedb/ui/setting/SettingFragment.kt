package com.project.gamedb.ui.setting

import android.content.Context
import android.view.View
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.ultis.Dialogs
import com.project.gamedb.ultis.assignViews
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : BaseFragment(), View.OnClickListener {
    private val dialogs = Dialogs()
    override val layoutResource: Int
        get() = R.layout.fragment_setting

    override fun startComponents() {
        handleClick()
    }

    private fun handleClick() {
        this.assignViews(textAbout, textFAQSupport, boxCheckPush, boxCheckNew)
    }

    private fun setSetting() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            if (boxCheckPush.isChecked) {
                putBoolean(getString(R.string.string_notify_update), true)
            } else {
                putBoolean(getString(R.string.string_notify_update), false)
            }
            if (boxCheckNew.isChecked) {
                putBoolean(getString(R.string.string_new_game), true)
            } else {
                putBoolean(getString(R.string.string_new_game), false)
            }
            apply()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textAbout -> {
                dialogs.setInform(getString(R.string.text_about))
                fragmentManager?.let { dialogs.show(it, getString(R.string.text_about)) }
            }
            R.id.textFAQSupport -> {
                dialogs.setInform(getString(R.string.text_FAQSupport))
                fragmentManager?.let { dialogs.show(it, getString(R.string.text_FAQSupport)) }
            }
            R.id.boxCheckPush, R.id.boxCheckNew -> setSetting()
        }
    }
}
