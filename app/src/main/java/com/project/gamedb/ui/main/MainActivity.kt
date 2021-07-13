package com.project.gamedb.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.project.gamedb.R
import com.project.gamedb.base.BaseActivity
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.base.OnQuerySearchListener
import com.project.gamedb.service.NotificationService
import com.project.gamedb.ui.genres.GenresFragment
import com.project.gamedb.ui.more.MoreFragment
import com.project.gamedb.ui.platform.PlatformFragment
import com.project.gamedb.ui.popular.PopularFragment
import com.project.gamedb.ui.ranking.RankingFragment
import com.project.gamedb.ui.saved.SavedGameFragment
import com.project.gamedb.ui.search.SearchFragment
import com.project.gamedb.ui.setting.SettingFragment
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class MainActivity : BaseActivity(), OnFragmentIntegrationListener.Open,
    OnFragmentIntegrationListener.Close {

    private val popularFragment = PopularFragment()
    private val platformFragment = PlatformFragment()
    private val genresFragment = GenresFragment()
    private val rankingFragment = RankingFragment()
    private val settingFragment = SettingFragment()
    private val searchFragment = SearchFragment()
    private val savedFragment = SavedGameFragment.getInstance()
    private val mainAdapter = MainAdapter(supportFragmentManager)
    private val queryListener: OnQuerySearchListener = searchFragment

    override val layoutResource: Int get() = R.layout.activity_main

    override fun initComponents() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigation)
        controlPage()
        if (!NotificationService.isAppRunning) initFragment()
        bottomNavigationView.selectedItemId = R.id.menuGames
        setSupportActionBar(toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
        }
        NotificationService.isAppRunning = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.menuSearch)?.actionView as SearchView
        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                searchView.isIconified = false
                switchFragment(searchFragment)
            } else {
                onBackPressed()
                openViewPager()
                searchView.isIconified = true
            }
        }
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { queryListener.querySearch(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { queryListener.querySearch(it) }
                    return false
                }
            })
        }
        return true
    }

    override fun openNewFragment(fragment: Fragment) {
        bottomNavigationView.visibility = View.GONE
        fragmentDetail.show()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentDetail, fragment)
            .setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right)
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

    override fun onDestroy() {
        NotificationService.isAppRunning = false
        super.onDestroy()
    }

    override fun pressBackButton() {
        onBackPressed()
    }

    private val onBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuGames -> openViewPager()
                R.id.menuGenres -> switchFragment(genresFragment)
                R.id.menuSaved -> switchFragment(savedFragment)
                R.id.menuRanking -> switchFragment(rankingFragment)
                R.id.menuSetting -> switchFragment(settingFragment)
            }
            true
        }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, genresFragment)
            .add(R.id.fragmentContainer, savedFragment)
            .add(R.id.fragmentContainer, settingFragment)
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
            TabLayout.ViewPagerOnTabSelectedListener(viewPagerFragment)
        )
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
