package com.project.gamedb.ui.platform

import android.view.View
import androidx.core.os.bundleOf
import com.project.gamedb.R
import com.project.gamedb.base.BaseFragment
import com.project.gamedb.ultis.assignViews
import kotlinx.android.synthetic.main.fragment_platform.*

class PlatformFragment : BaseFragment(), View.OnClickListener {
    override val layoutResource get() = R.layout.fragment_platform

    override fun startComponents() {
        handleClick()
    }

    override fun onClick(v: View?) {
        val platform = when (v?.id) {
            R.id.imagePcPlatform -> getString(R.string.text_pc)
            R.id.imagePsPlatform -> getString(R.string.text_ps)
            R.id.imageXboxPlatform -> getString(R.string.text_xbox)
            R.id.imageNintendoPlatform -> getString(R.string.text_nintendo)
            else -> getString(R.string.text_mobile)
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
}
