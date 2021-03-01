package com.project.gamedb.ui.feature

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import com.project.gamedb.R
import com.project.gamedb.base.BaseAdapter
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.data.model.Games
import com.project.gamedb.ultis.getScreenWidth

class FeatureAdapter : BaseAdapter<Games>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Games> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_feature, parent, false).getScreenWidth(2)
        return FeatureViewHolder(view)
    }
}
