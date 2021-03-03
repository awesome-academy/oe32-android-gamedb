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
import com.project.gamedb.ui.genres.GenresFragment
import com.project.gamedb.ui.platform.PlatformFragment
import com.project.gamedb.ui.popular.PopularFragment
import com.project.gamedb.ui.ranking.RankingFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class MainActivity : BaseActivity() {
    private val popularFragment = PopularFragment()
    private val platformFragment = PlatformFragment()
    private val genresFragment = GenresFragment()
    private val rankingFragment = RankingFragment()
    private val mainAdapter = MainAdapter(supportFragmentManager)

    override val layoutResource: Int get() = R.layout.activity_main

    override fun initComponents() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigation)
        initFragment()
        controlPage()
        bottomNavigationView.selectedItemId = R.id.menuGames
        setSupportActionBar(toolbar)
    }

    private val onBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuGames -> openViewPager()
                R.id.menuGenres -> openFragment(genresFragment)
                R.id.menuRanking -> openFragment(rankingFragment)
            }
            true
        }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, genresFragment)
            .add(R.id.fragmentContainer, rankingFragment)
            .commit()
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
