package org.vizhev.certificate.vu.fortyfive.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import kotlinx.android.synthetic.main.item_set_data_general.view.*
import org.vizhev.certificate.vu.fortyfive.R

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var contentList: List<String>

    fun setContent(contentList: List<String>) {
        this.contentList = contentList
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        }
        return 1
    }

    override fun onCreateViewHolder(parrent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> ViewHolderGeneral(
                    LayoutInflater
                            .from(parrent.context)
                            .inflate(R.layout.item_set_data_general, parrent, false)
            )
            else -> ViewHolderParams(
                    LayoutInflater
                            .from(parrent.context)
                            .inflate(R.layout.item_set_data_params, parrent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

    }

    class ViewHolderGeneral(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etGeneralDate: EditText = itemView.et_set_data_general_date
    }

    class ViewHolderParams(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}