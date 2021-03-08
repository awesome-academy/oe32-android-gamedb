package com.project.gamedb.ui.saved

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnLongClickHandler
import com.project.gamedb.data.model.GameSaved
import com.project.gamedb.ui.details.DetailsFragment
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.ui.main.MainActivity
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_saved.*
import kotlinx.android.synthetic.main.item_recyclerview_saved.view.*

class SavedGameFragment : BaseFragment(), SavedGameContract.View, OnLongClickHandler,
    View.OnClickListener {
    private var savedAdapter = SavedGameAdapter()
    private var savedGamePresenter: SavedGamePresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Open? = null

    override var chooseState: Boolean = false

    override val layoutResource: Int
        get() = R.layout.fragment_saved

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun startComponents() {
        initAdapter()
        initPresenter()
        initData()
        initView()
    }

    override fun showGame(list: List<GameSaved>) {
        savedAdapter.replaceData(list)
    }

    override fun showResult(result: String) {
        context?.showToast(result, Toast.LENGTH_SHORT)
    }

    override fun handleDeleteButton() {
        if (chooseState) buttonDelete.visibility = Button.VISIBLE
        else buttonDelete.visibility = Button.GONE
    }

    override fun openGameDetail(id: Int, genres: String) {
        mainCallback?.openNewFragment(DetailsFragment(id, genres))
    }

    override fun onClick(v: View?) {
        buttonDelete.visibility = Button.GONE
        chooseState = false
        val positions = savedAdapter.getPositionList(removeList)
        for (position in positions) {
            recyclerSaved.layoutManager?.findViewByPosition(position)?.boxChecked?.visibility =
                CheckBox.GONE
        }
        for (game in removeList) {
            savedGamePresenter?.removeGame(game.id, getString(R.string.remove_success))
            savedAdapter.removeData(game)
        }
        removeList.clear()
    }

    private fun initData() {
        savedGamePresenter?.getGame()
    }

    private fun initView() {
        buttonDelete.visibility = Button.GONE
        buttonDelete.setOnClickListener(this)
    }

    private fun initAdapter() {
        recyclerSaved.adapter = savedAdapter
    }

    private fun initPresenter() {
        val repository = Repositories.getLocalRepository(context ?: return)
        savedGamePresenter = SavedGamePresenter(this, repository)
    }

    companion object {
        private var instance: SavedGameFragment? = null
        var removeList = mutableListOf<GameSaved>()

        fun getInstance() = instance ?: SavedGameFragment().also { instance = it }
    }
}
