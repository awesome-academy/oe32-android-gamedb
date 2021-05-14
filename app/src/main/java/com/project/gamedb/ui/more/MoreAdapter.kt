package com.project.gamedb.ui.more

import android.view.LayoutInflater
import android.view.ViewGroup
import com.project.gamedb.R
import com.project.gamedb.base.BaseAdapter
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.base.OnClickFragmentListener
import com.project.gamedb.data.model.Games

class MoreAdapter(private val OnClickDetailsListener: OnClickFragmentListener.Details) :
    BaseAdapter<Games>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Games> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_feature, parent, false)
        return MoreViewHolder(view, OnClickDetailsListener)
    }
}
