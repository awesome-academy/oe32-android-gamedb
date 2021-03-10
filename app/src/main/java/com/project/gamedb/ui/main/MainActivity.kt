package com.project.gamedb.ui.main

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.project.gamedb.R
import com.project.gamedb.base.BaseActivity
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.ui.genres.GenresFragment
import com.project.gamedb.ui.more.MoreFragment
import com.project.gamedb.ui.platform.PlatformFragment
import com.project.gamedb.ui.popular.PopularFragment
import com.project.gamedb.ui.ranking.RankingFragment
import com.project.gamedb.ui.saved.SavedGameFragment
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class MainActivity : BaseActivity(), OnFragmentIntegrationListener.Open,
    OnFragmentIntegrationListener.Close  {
    private val popularFragment = PopularFragment()
    private val platformFragment = PlatformFragment()
    private val genresFragment = GenresFragment()
    private val rankingFragment = RankingFragment()
    private val savedFragment = SavedGameFragment.getInstance()
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

    override fun openNewFragment(fragment: Fragment) {
        bottomNavigationView.visibility = View.GONE
        fragmentDetail.show()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentDetail, fragment)
            .addToBackStack(getString(R.string.string_fragment))
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) supportFragmentManager.popBackStackImmediate()
        if (supportFragmentManager.fragments.lastOrNull() !is MoreFragment) {
            bottomNavigationView.show()
            fragmentDetail.hide()
        }
    }

    override fun pressBackButton() {
        onBackPressed()
    }

    private val onBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuGames -> openViewPager()
                R.id.menuGenres -> switchFragment(genresFragment)
                R.id.menuRanking -> switchFragment(rankingFragment)
                R.id.menuSaved -> switchFragment(savedFragment)
            }
            true
        }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, genresFragment)
            .add(R.id.fragmentContainer, rankingFragment)
            .add(R.id.fragmentContainer, savedFragment)
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
