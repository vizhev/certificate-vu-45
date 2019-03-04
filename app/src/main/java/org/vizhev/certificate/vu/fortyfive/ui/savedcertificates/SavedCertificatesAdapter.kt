package org.vizhev.certificate.vu.fortyfive.ui.savedcertificates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData

class SavedCertificatesAdapter : RecyclerView.Adapter<SavedCertificatesAdapter.ViewHolder>() {

    private val mContentList = mutableListOf<CertificateData>()

    fun setContent(contentList: List<CertificateData>) {
        mContentList.addAll(contentList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_result, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}