package com.project.gamedb.ui.more

import android.content.Context
import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnClickDetailsListener
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.data.source.remote.api.ApiConstants
import com.project.gamedb.ui.details.DetailsFragment
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment(
    private val id: String,
    private val ordering: String,
) : BaseFragment(), MoreContract.View, OnClickDetailsListener {

    private var moreAdapter: MoreAdapter? = null
    private var morePresenter: MorePresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Open? = null

    override val layoutResource get() = R.layout.fragment_more

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun startComponents() {
        initAdapter()
        initPresenter()
        initData()
    }

    override fun showGames(result: ResultGames) {
        textMoreLoading?.hide()
        moreAdapter?.replaceData(result.results)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_SHORT)
    }

    override fun showInfo(info: String) {
        textMoreInfo.text = info
    }

    override fun openGameDetail(id: Int, genres: String) {
        mainCallback?.openNewFragment(DetailsFragment(id, genres))
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        morePresenter = MorePresenter(this, repository)
    }

    private fun initAdapter() {
        moreAdapter = MoreAdapter(this)
        recyclerMore.adapter = moreAdapter
    }

    private fun initData() {
        when (ordering) {
            ApiConstants.GENRES -> morePresenter?.apply {
                getGamesOrdered(ordering, id)
                getInfo(id)
            }
            else -> morePresenter?.getGamesOrdered(ordering, id)
        }
    }
}
