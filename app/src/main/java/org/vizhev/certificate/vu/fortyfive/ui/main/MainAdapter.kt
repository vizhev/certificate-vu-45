package org.vizhev.certificate.vu.fortyfive.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.vizhev.certificate.vu.fortyfive.R

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    lateinit var contentList: List<String>

    fun setContent(contentList: List<String>) {
        this.contentList = contentList
    }

    override fun onCreateViewHolder(parrent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parrent.context).inflate(R.layout.item_set_data_general, parrent, false)
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}