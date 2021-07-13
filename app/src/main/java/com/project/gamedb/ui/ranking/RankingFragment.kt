package com.project.gamedb.ui.ranking

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnClickFragmentListener
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.ui.details.DetailsFragment
import com.project.gamedb.ultis.Repositories
import com.project.gamedb.ultis.hide
import com.project.gamedb.ultis.show
import com.project.gamedb.ultis.showToast
import kotlinx.android.synthetic.main.fragment_ranking.*
import java.util.*

class RankingFragment : BaseFragment(), RankingContract.View, OnClickFragmentListener.Details {
    private val rankingAdapter: RankingAdapter = RankingAdapter(this)
    private var rankingPresenter: RankingPresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Open? = null
    private var isLoaded = false

    override val layoutResource: Int
        get() = R.layout.fragment_ranking

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun startComponents() {
        initAdapter()
        initPresenter()
        initData()
    }

    override fun showGame(list: ResultGames) {
        textRankingLoading?.hide()
        rankingAdapter.replaceData(list.results)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_SHORT)
    }

    private fun initData() {
        rankingPresenter?.getRankedGame(Calendar.getInstance().get(Calendar.YEAR) - 1)
    }

    private fun initAdapter() {
        recyclerRanking.adapter = rankingAdapter
        textFilter.setOnClickListener {
            openYearDialog(
                Calendar.getInstance().get(Calendar.YEAR) - 1
            )
        }
        if (!isLoaded) {
            textRankingLoading.text = getString(R.string.text_Loading)
            isLoaded = true
        }
    }

    private fun initPresenter() {
        val repository = Repositories.getRemoteRepository(GameRemoteDataSource.getInstance())
        rankingPresenter = RankingPresenter(this, repository)
    }

    override fun openGameDetail(id: Int, genres: String) {
        mainCallback?.openNewFragment(DetailsFragment(id, genres))
    }

    private fun openYearDialog(year: Int) {
        val years = Array(10) { i -> i.toString() }
        for (i in 0..9) {
            years[i] = (year - i).toString()
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.text_choose_year)
        builder.setItems(years) { _, which ->
            rankingPresenter?.getRankedGame(years[which].toInt())
            rankingAdapter.replaceData(arrayListOf())
            textRankingLoading.show()
            textRankingLoading.text = getString(R.string.text_Loading)
        }
        val dialog = builder.create()
        dialog.show()
    }
}
