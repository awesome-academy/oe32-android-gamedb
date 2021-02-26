package com.project.gamedb.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.gamedb.R
import com.project.gamedb.base.BaseActivity
import com.project.gamedb.ui.popular.PopularFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val popularFragment = PopularFragment()

    override val layoutResource: Int get() = R.layout.activity_main

    override fun initComponents() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigation)
        initFragment()
        bottomNavigationView.selectedItemId = R.id.menuGames
    }

    private val onBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuGames -> openViewPager()
            }
            true
        }

    private fun initFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.viewPagerFragment, popularFragment)
        transaction.commit()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
