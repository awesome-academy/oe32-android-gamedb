package com.project.gamedb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.project.gamedb.R
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment : Fragment() {

    @get: LayoutRes
    protected abstract val layoutResource: Int

    protected abstract fun startComponents()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutResource, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startComponents()
    }

    protected fun openFragment(fragment: Fragment) {
        fragmentContainer.visibility = View.VISIBLE
        viewPagerFragment.visibility = View.GONE
        tabLayout.visibility = View.GONE
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, fragment)?.commit()
    }
}
