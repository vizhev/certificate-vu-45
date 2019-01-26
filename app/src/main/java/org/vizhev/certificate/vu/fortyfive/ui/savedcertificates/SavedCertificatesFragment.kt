package org.vizhev.certificate.vu.fortyfive.ui.savedcertificates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseFragment

class SavedCertificatesFragment : BaseFragment() {

    companion object {
        const val TAG: String = "SavedCertificatesFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved_certificates, container, false)
    }
}