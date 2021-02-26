package com.project.gamedb.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainAdapter internal constructor(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    private var fragments = listOf<Fragment>()
    private var titles = listOf<String>()

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    fun setItemFragment(fragments: List<Fragment>, titles: List<String>) {
        this.fragments = fragments
        this.titles = titles
    }
}
