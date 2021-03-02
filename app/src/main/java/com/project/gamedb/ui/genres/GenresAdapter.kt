package com.project.gamedb.ui.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import com.project.gamedb.R
import com.project.gamedb.base.BaseAdapter
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.data.model.Genres

class GenresAdapter : BaseAdapter<Genres>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Genres> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_genres, parent, false)
        return GenresViewHolder(view)
    }
}
