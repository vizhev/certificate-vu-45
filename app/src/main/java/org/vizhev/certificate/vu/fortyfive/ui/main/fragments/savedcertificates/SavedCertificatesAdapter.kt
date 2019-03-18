package org.vizhev.certificate.vu.fortyfive.ui.main.fragments.savedcertificates

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent
import org.vizhev.certificate.vu.fortyfive.ui.main.MainUiState

private const val LOG_TAG = "SavedItemsAdapterLog"

class SavedCertificatesAdapter : RecyclerView.Adapter<SavedCertificatesAdapter.ViewHolder>() {

    private val mContentList = mutableListOf<CertificateContent>()
    private val mSelectedItemsMap = mutableSetOf<Long>()
    private var mSelectedColor: Int = Color.RED
    private var mBackgroundColor: Int = Color.WHITE
    private var mExpandedPosition: Int = -1
    private var mPreviousExpandedPosition: Int = -1
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mOnSelectItemsListener: OnSelectItemsListener? = null

    interface OnSelectItemsListener {

        fun showDeleteAction()

        fun hideDeleteAction()
    }

    fun setContent(contentList: List<CertificateContent>) {
        mContentList.clear()
        mContentList.addAll(contentList)
        notifyDataSetChanged()
    }

    fun setItem(certificateContent: CertificateContent) {
        mContentList.add(0, certificateContent)
        notifyDataSetChanged()
    }

    fun setLayoutManager(linearLayoutManager: LinearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager
    }

    fun setOnSelectItemsListener(onSelectItemsListener: OnSelectItemsListener) {
        mOnSelectItemsListener = onSelectItemsListener
    }

    fun setColors(backgroundColor: Int, selectColor: Int) {
        mBackgroundColor = backgroundColor
        mSelectedColor = selectColor
    }

    fun getContent(): List<CertificateContent> {
        return mContentList
    }

    fun getSelectedItems(): Set<Long> {
        return mSelectedItemsMap
    }

    fun removeSelectedItems() {
        mExpandedPosition = -1
        val contentList = mutableListOf<CertificateContent>()
        mContentList.forEach {
            if (!mSelectedItemsMap.contains(it.id)) {
                contentList.add(it)
            } else {
                notifyItemRemoved(mContentList.indexOf(it))
            }
            notifyItemChanged(mContentList.indexOf(it))
        }
        mContentList.clear()
        mContentList.addAll(contentList)
        mSelectedItemsMap.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_saved_certificate, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val certificateContent = mContentList[position]
        val isExpanded = mExpandedPosition == position
        if (isExpanded) {
            mPreviousExpandedPosition = position
        }
        holder.tvStationStamp.text = certificateContent.stationStamp
        holder.tvIssueTime.text = certificateContent.issueTime
        holder.tvDate.text = certificateContent.date
        holder.tvLocomotiveSeries.text = certificateContent.locomotiveSeries
        holder.tvTrainNumber.text = certificateContent.trainNumber
        holder.tvLastWagonNumber.text = certificateContent.lastWagonNumber
        holder.tvWeight.text = certificateContent.weight
        holder.tvAxesTwoAndHalf.text = certificateContent.axesTwoAndHalf
        holder.tvAxesThreeAndHalf.text = certificateContent.axesThreeAndHalf
        holder.tvAxesFive.text = certificateContent.axesFive
        holder.tvAxesSix.text = certificateContent.axesSix
        holder.tvAxesSixAndHalf.text = certificateContent.axesSixAndHalf
        holder.tvAxesSeven.text = certificateContent.axesSeven
        holder.tvAxesSevenAndHalf.text = certificateContent.axesSevenAndHalf
        holder.tvAxesEight.text = certificateContent.axesEight
        holder.tvAxesEightAndHalf.text = certificateContent.axesEightAndHalf
        holder.tvAxesNine.text = certificateContent.axesNine
        holder.tvAxesTen.text = certificateContent.axesTen
        holder.tvAxesTwelve.text = certificateContent.axesTwelve
        holder.tvAxesFifteen.text = certificateContent.axesFifteen
        holder.tvPressingPadsTwoAndHalf.text = certificateContent.pressingPadsTwoAndHalf
        holder.tvPressingPadsThreeAndHalf.text = certificateContent.pressingPadsThreeAndHalf
        holder.tvPressingPadsFive.text = certificateContent.pressingPadsFive
        holder.tvPressingPadsSix.text = certificateContent.pressingPadsSix
        holder.tvPressingPadsSixAndHalf.text = certificateContent.pressingPadsSixAndHalf
        holder.tvPressingPadsSeven.text = certificateContent.pressingPadsSeven
        holder.tvPressingPadsSevenAndHalf.text = certificateContent.pressingPadsSevenAndHalf
        holder.tvPressingPadsEight.text = certificateContent.pressingPadsEight
        holder.tvPressingPadsEightAndHalf.text = certificateContent.pressingPadsEightAndHalf
        holder.tvPressingPadsNine.text = certificateContent.pressingPadsNine
        holder.tvPressingPadsTen.text = certificateContent.pressingPadsTen
        holder.tvPressingPadsTwelve.text = certificateContent.pressingPadsTwelve
        holder.tvPressingPadsFifteen.text = certificateContent.pressingPadsFifteen
        holder.tvTotalAxes.text = certificateContent.totalAxes
        holder.tvPressingPadsRequired.text = certificateContent.pressingPadsRequired
        holder.tvHandBrakesRequired.text = certificateContent.handBrakesRequired
        holder.clItemBody.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.llItemTitle.text = StringBuilder()
                .append(certificateContent.date)
                .append("  ")
                .append(certificateContent.issueTime)
                .toString()
        holder.cvItem.setOnClickListener {
            mExpandedPosition = when (isExpanded) {
                true -> -1
                false -> position
            }
            notifyItemChanged(mPreviousExpandedPosition)
            notifyItemChanged(position)
            if (mLinearLayoutManager != null) {
                mLinearLayoutManager!!.scrollToPositionWithOffset(position, 0)
            }
        }
        val backgroundColor = when (mSelectedItemsMap.contains(certificateContent.id)) {
            true -> mSelectedColor
            false -> mBackgroundColor
        }
        holder.cvItem.setCardBackgroundColor(backgroundColor)
        holder.cvItem.setOnLongClickListener {
            val isPositionSelected = mSelectedItemsMap.contains(certificateContent.id)
            when (isPositionSelected) {
                true -> mSelectedItemsMap.remove(certificateContent.id)
                false -> mSelectedItemsMap.add(certificateContent.id)
            }
            val isItemSelected = !mSelectedItemsMap.isEmpty()
            MainUiState.isSavedItemSelected = isItemSelected
            if (mOnSelectItemsListener != null) {
                when (isItemSelected) {
                    true -> mOnSelectItemsListener!!.showDeleteAction()
                    false -> mOnSelectItemsListener!!.hideDeleteAction()
                }
            }
            notifyItemChanged(position)
            Log.d(LOG_TAG, "is item selected = $isItemSelected")
            Log.d(LOG_TAG, "selected items size = ${mSelectedItemsMap.size}")
            true
        }
    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvStationStamp: TextView = itemView.findViewById(R.id.tv_result_station_stamp)
        val tvIssueTime: TextView = itemView.findViewById(R.id.tv_result_issue_time)
        val tvDate: TextView = itemView.findViewById(R.id.tv_result_date)
        val tvLocomotiveSeries: TextView = itemView.findViewById(R.id.tv_result_locomotive_series)
        val tvTrainNumber: TextView = itemView.findViewById(R.id.tv_result_train_number)
        val tvLastWagonNumber: TextView = itemView.findViewById(R.id.tv_result_last_wagon_number)
        val tvWeight: TextView = itemView.findViewById(R.id.tv_result_weight)
        val tvAxesTwoAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_two_and_half)
        val tvAxesThreeAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_three_and_half)
        val tvAxesFive: TextView = itemView.findViewById(R.id.tv_result_axes_five)
        val tvAxesSix: TextView = itemView.findViewById(R.id.tv_result_axes_six)
        val tvAxesSixAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_six_and_half)
        val tvAxesSeven: TextView = itemView.findViewById(R.id.tv_result_axes_seven)
        val tvAxesSevenAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_seven_and_half)
        val tvAxesEight: TextView = itemView.findViewById(R.id.tv_result_axes_eight)
        val tvAxesEightAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_eight_and_half)
        val tvAxesNine: TextView = itemView.findViewById(R.id.tv_result_axes_nine)
        val tvAxesTen: TextView = itemView.findViewById(R.id.tv_result_axes_ten)
        val tvAxesTwelve: TextView = itemView.findViewById(R.id.tv_result_axes_twelve)
        val tvAxesFifteen: TextView = itemView.findViewById(R.id.tv_result_axes_fifteen)
        val tvPressingPadsTwoAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_two_and_half)
        val tvPressingPadsThreeAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_three_and_half)
        val tvPressingPadsFive: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_five)
        val tvPressingPadsSix: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_six)
        val tvPressingPadsSixAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_six_and_half)
        val tvPressingPadsSeven: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_seven)
        val tvPressingPadsSevenAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_seven_and_half)
        val tvPressingPadsEight: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_eight)
        val tvPressingPadsEightAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_eight_and_half)
        val tvPressingPadsNine: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_nine)
        val tvPressingPadsTen: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_ten)
        val tvPressingPadsTwelve: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_twelve)
        val tvPressingPadsFifteen: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_fifteen)
        val tvTotalAxes: TextView = itemView.findViewById(R.id.tv_result_total_axes)
        val tvPressingPadsRequired: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_required)
        val tvHandBrakesRequired: TextView = itemView.findViewById(R.id.tv_result_hand_brakes_required)
        val llItemTitle: TextView = itemView.findViewById(R.id.tv_saved_certificate_item_title)
        val clItemBody: ConstraintLayout = itemView.findViewById(R.id.cl_saved_certificate_body)
        val cvItem: CardView = itemView.findViewById(R.id.cv_item_saved_certificate)
    }
}