package com.project.gamedb.ui.setting

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.view.View
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.service.NotificationService
import com.project.gamedb.ultis.Dialogs
import com.project.gamedb.ultis.NotificationBroadcast
import com.project.gamedb.ultis.assignViews
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.item_recyclerview_saved.*


class SettingFragment : BaseFragment(), View.OnClickListener {
    override val layoutResource: Int
        get() = R.layout.fragment_setting

    private val dialogs = Dialogs()

    override fun startComponents() {
        handleClick()
        initSetting()
        controlNotification()
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
            R.id.boxCheckNew -> setSetting()
        }
    }

    private fun handleClick() {
        this.assignViews(textAbout, textFAQSupport, boxCheckNew)
    }

    private fun initSetting() {
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.string_random_game), Context.MODE_PRIVATE
        )
        boxCheckNew.isChecked = sharedPref?.getBoolean(getString(R.string.string_random_game), false) == true
    }

    private fun setSetting() {
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.string_random_game), Context.MODE_PRIVATE
        )
        with(sharedPref?.edit()) {
            if (boxCheckNew.isChecked) {
                this?.putBoolean(getString(R.string.string_random_game), true)
                initSetting()
            } else {
                this?.putBoolean(getString(R.string.string_random_game), false)
            }
            this?.apply()
        }
    }

    private fun controlNotification() {
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.string_random_game), Context.MODE_PRIVATE
        )
        if (sharedPref?.getBoolean(getString(R.string.string_random_game), false) == true) {
            setNotification()
        } else {
            cancelNotification()
        }
    }

    private fun setNotification() {
        val alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationBroadcast::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmMgr.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            pendingIntent
        )
    }

    private fun cancelNotification() {
        val alarmManager =
            context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, NotificationBroadcast::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager?.cancel(pendingIntent)
    }
}
