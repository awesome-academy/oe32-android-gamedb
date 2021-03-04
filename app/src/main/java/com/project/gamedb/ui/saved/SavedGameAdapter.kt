package com.project.gamedb.ui.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import com.project.gamedb.R
import com.project.gamedb.base.BaseAdapter
import com.project.gamedb.base.BaseViewHolder
import com.project.gamedb.data.model.GameSaved
import kotlinx.android.synthetic.main.item_recyclerview_saved.view.*

class SavedGameAdapter : BaseAdapter<GameSaved>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GameSaved> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_saved, parent, false)
        view.boxChecked.visibility = CheckBox.GONE
        return SavedGameViewHolder(view)
    }
}
