package com.project.gamedb.ui.more

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.Toast
import androidx.core.os.bundleOf
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnClickDetailsListener
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.data.model.ResultGames
import com.project.gamedb.data.source.remote.GameRemoteDataSource
import com.project.gamedb.data.source.remote.api.ApiConstants
import com.project.gamedb.ui.details.DetailsFragment
import com.project.gamedb.ultis.*
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment : BaseFragment(), MoreContract.View, OnClickDetailsListener {
    override val layoutResource get() = R.layout.fragment_more

    private var moreAdapter: MoreAdapter? = null
    private var morePresenter: MorePresenter? = null
    private var mainCallback: OnFragmentIntegrationListener.Open? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun startComponents() {
        textAbout.hide()
        textPopular.hide()
        initAdapter()
        initPresenter()
        initData()
    }

    override fun showGames(result: ResultGames) {
        textMoreLoading?.hide()
        textPopular?.show()
        moreAdapter?.replaceData(result.results)
    }

    override fun showError(error: String) {
        context?.showToast(error, Toast.LENGTH_SHORT)
    }

    override fun showInfo(info: String) {
        textAbout?.show()
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
        if (ARGUMENT_ORDERING == ApiConstants.GENRES) {
            morePresenter?.apply {
                getInfo(ARGUMENT_ID)
                getGamesOrdered(ARGUMENT_ORDERING, ARGUMENT_ID)
            }
        } else {
            morePresenter?.getGamesOrdered(ARGUMENT_ORDERING, ARGUMENT_ID)
        }
    }

    companion object {
        private const val ARGUMENT_ID: String = "ARGUMENT_ID"
        private const val ARGUMENT_ORDERING: String = "ARGUMENT_ORDERING"

        fun newInstance(id: String, ordering: String) = MoreFragment().apply {
            arguments = bundleOf(ARGUMENT_ID to id, ARGUMENT_ORDERING to ordering)
        }
    }
}
