package com.project.gamedb.ui.platform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.base.OnFragmentIntegrationListener
import com.project.gamedb.data.model.Platform
import com.project.gamedb.data.source.remote.api.ApiConstants
import com.project.gamedb.ui.more.MoreFragment
import com.project.gamedb.ultis.assignViews
import kotlinx.android.synthetic.main.fragment_platform.*

class PlatformFragment : BaseFragment(), View.OnClickListener {
    private var mainCallback: OnFragmentIntegrationListener.Open? = null

    override val layoutResource get() = R.layout.fragment_platform

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentIntegrationListener.Open) mainCallback = context
    }

    override fun startComponents() {
        handleClick()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imagePcPlatform -> openPlatformDetails(getString(R.string.text_pc))
            R.id.imagePsPlatform -> openPlatformDetails(getString(R.string.text_ps))
            R.id.imageXboxPlatform -> openPlatformDetails(getString(R.string.text_xbox))
            R.id.imageNintendoPlatform -> openPlatformDetails(getString(R.string.text_nintendo))
            else -> openPlatformDetails(getString(R.string.text_mobile))
        }
    }

    private fun handleClick() {
        this.assignViews(
            imagePcPlatform,
            imageMobilePlatform,
            imageXboxPlatform,
            imagePsPlatform,
            imageNintendoPlatform
        )
    }

    private fun openPlatformDetails(id: String) {
        mainCallback?.openNewFragment(
            (MoreFragment(
                ApiConstants.PLATFORM,
                ApiConstants.PLATFORM,
            ))
        )
    }
}
