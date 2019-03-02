package org.vizhev.certificate.vu.fortyfive.ui.savedcertificates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.main.MainViewModel

class SavedCertificatesFragment : Fragment() {

    private lateinit var mMainViewModel: MainViewModel

    companion object {
        const val TAG: String = "SavedCertificatesFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved_certificates, container, false)
    }
}