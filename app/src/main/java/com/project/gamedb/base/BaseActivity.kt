package com.project.gamedb.base

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TableLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
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

    protected fun openFragment(fragment: Fragment) {
        fragmentContainer.visibility = FrameLayout.VISIBLE
        viewPagerFragment.visibility = ViewPager.GONE
        tabLayout.visibility = TableLayout.GONE
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
    }

    protected fun openViewPager(){
        fragmentContainer.visibility = FrameLayout.GONE
        viewPagerFragment.visibility = ViewPager.VISIBLE
        tabLayout.visibility = TableLayout.VISIBLE
    }

}
