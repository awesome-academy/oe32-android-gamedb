package com.project.gamedb.base

import androidx.fragment.app.Fragment

interface OnFragmentIntegrationListener {
    interface Open {
        fun openNewFragment(fragment: Fragment)
    }

    interface Close {
        fun pressBackButton()
    }
}
