package com.project.gamedb.ui.splash

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.project.gamedb.R
import com.project.gamedb.ui.main.MainActivity
import com.project.gamedb.ultis.NetworkConnection
import kotlin.system.exitProcess

@RequiresApi(Build.VERSION_CODES.M)
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkConnection(applicationContext)
    }

    private fun checkConnection(context: Context) {
        if (NetworkConnection().isOnline(context)) {
            startActivity(MainActivity.getIntent(this))
            finish()
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(getString(R.string.text_no_internet))
            builder.setCancelable(false)
            builder.setPositiveButton(
                getString(R.string.text_exit)
            ) { _, _ -> exitProcess(0) }
            builder.create().show()
        }
    }
}
