package org.vizhev.certificate.vu.fortyfive.ui.main.fragments.savedcertificates

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.main.MainActivity
import org.vizhev.certificate.vu.fortyfive.ui.main.MainViewModel

class SavedCertificatesFragment : Fragment() {

    private lateinit var mRvSavedCertificates: RecyclerView
    private lateinit var mMainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mMainViewModel = (context as MainActivity).mMainViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_saved_certificates, container, false)
        mRvSavedCertificates = view.findViewById(R.id.rv_saved_certificates)
        return view
    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = mMainViewModel.getSavedCertificatesAdapter()
        val layoutManager = LinearLayoutManager(context)
        adapter.setLayoutManager(layoutManager)
        adapter.setColors(
                resources.getColor(R.color.colorBackgroundLight),
                resources.getColor(R.color.colorSelectedItem)
        )
        mMainViewModel.loadSavedCertificates()
        mRvSavedCertificates.apply{
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }
}