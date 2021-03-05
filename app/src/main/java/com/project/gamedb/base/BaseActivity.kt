package com.project.gamedb.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.gamedb.R
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity(), BaseView {

    @get: LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initComponents()
    }

    protected abstract fun initComponents()

    protected fun switchFragment(fragment: Fragment) {
        fragmentContainer.visibility = View.VISIBLE
        viewPagerFragment.visibility = View.GONE
        tabLayout.visibility = View.GONE
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(getString(R.string.string_fragment))
            .commit()
    }

    protected fun openViewPager() {
        fragmentContainer.visibility = View.GONE
        viewPagerFragment.visibility = View.VISIBLE
        tabLayout.visibility = View.VISIBLE
    }
}
