package com.project.gamedb.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import com.project.gamedb.R
import com.project.gamedb.base.BaseAdapter
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.base.OnClickFragmentListener
import com.project.gamedb.data.model.Games

class PopularAdapter(private val anOnClickFragmentListener: OnClickFragmentListener.Details) :
    BaseAdapter<Games>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Games> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_main, parent, false)
        return PopularViewHolder(view, anOnClickFragmentListener)
    }
}
