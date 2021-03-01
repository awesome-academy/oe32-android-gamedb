package com.project.gamedb.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.project.gamedb.R
import com.project.gamedb.base.BaseActivity
import com.project.gamedb.ui.platform.PlatformFragment
import com.project.gamedb.ui.popular.PopularFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val popularFragment = PopularFragment()
    private val platformFragment = PlatformFragment()
    private val mainAdapter: MainAdapter = MainAdapter(supportFragmentManager)

    override val layoutResource: Int get() = R.layout.activity_main

    override fun initComponents() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigation)
        initFragment()
        controlPage()
        bottomNavigationView.selectedItemId = R.id.menuGames
    }

    private val onBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuGames -> openViewPager()
            }
            true
        }

    private fun initFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.commit()
    }

    private fun controlPage() {
        val fragments = listOf<Fragment>(popularFragment, platformFragment)
        val titles = listOf(getString(R.string.string_popular), getString(R.string.string_platform))
        mainAdapter.setItemFragment(fragments, titles)
        viewPagerFragment.adapter = mainAdapter
        tabLayout.setupWithViewPager(viewPagerFragment)
        viewPagerFragment.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                viewPagerFragment
            )
        )
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
