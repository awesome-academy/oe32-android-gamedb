package com.project.gamedb.ui.ranking

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnClickDetailsListener
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ui.details.DetailsFragment
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_ranking.*
import java.util.*

class RankingFragment : BaseFragment(), RankingContract.View, OnClickDetailsListener {
    private var rankingAdapter: RankingAdapter? = null
    private var rankingPresenter: RankingPresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Open? = null

    override val layoutResource: Int
        get() = R.layout.fragment_ranking

    init {
        rankingAdapter = RankingAdapter(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun startComponents() {
        initAdapter()
        initPresenter()
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loaded = savedInstanceState?.getBoolean(getString(R.string.text_Loading))
        if (loaded == true) textRankingLoading.hide() else textRankingLoading.text =
            getString(R.string.text_Loading)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(getString(R.string.text_Loading), true)
    }

    override fun showGame(list: ResultGames) {
        textRankingLoading?.hide()
        rankingAdapter?.replaceData(list.results)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_SHORT)
    }

    private fun initData() {
        rankingPresenter?.getRankedGame(Calendar.getInstance().get(Calendar.YEAR) - 1)
    }

    private fun initAdapter() {
        recyclerRanking.adapter = rankingAdapter
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        rankingPresenter = RankingPresenter(this, repository)
    }

    override fun openGameDetail(id: Int, genres: String) {
        mainCallback?.openNewFragment(DetailsFragment(id, genres))
    }
}
