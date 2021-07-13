package com.project.gamedb.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.gamedb.R
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.show
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity(), BaseView {

    @get: LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initComponents()
        fragmentDetail.hide()
    }

    protected abstract fun initComponents()

    protected fun switchFragment(fragment: Fragment) {
        fragmentContainer.show()
        viewPagerFragment.hide()
        tabLayout.hide()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right)
            .addToBackStack(getString(R.string.string_fragment))
            .commit()
    }

    protected fun openViewPager() {
        fragmentContainer.hide()
        viewPagerFragment.show()
        tabLayout.show()
    }

}
