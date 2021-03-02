package com.project.gamedb.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.gamedb.R
import com.project.gamedb.ui.main.MainActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
