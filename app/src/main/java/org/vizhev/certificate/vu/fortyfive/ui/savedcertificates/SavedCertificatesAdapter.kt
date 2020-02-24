package org.vizhev.certificate.vu.fortyfive.ui.savedcertificates

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
import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent
import org.vizhev.certificate.vu.fortyfive.ui.main.MainUiState

class SavedCertificatesAdapter : RecyclerView.Adapter<SavedCertificatesAdapter.ViewHolder>() {

    private val mContentList = mutableListOf<CertificateContent>()
    private val mSelectedItemsMap = mutableSetOf<Long>()
    private var mSelectedColor: Int = Color.RED
    private var mBackgroundColor: Int = Color.WHITE
    private var mExpandedPosition: Int = -1
    private var mPreviousExpandedPosition: Int = -1
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mOnSelectItemsListener: OnSelectItemsListener? = null

    companion object {
        private const val LOG_TAG = "SavedItemsAdapterLog"
    }

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
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvStationStamp: TextView = itemView.findViewById(R.id.tv_result_station_stamp)
        private val tvIssueTime: TextView = itemView.findViewById(R.id.tv_result_issue_time)
        private val tvDate: TextView = itemView.findViewById(R.id.tv_result_date)
        private val tvLocomotiveSeries: TextView = itemView.findViewById(R.id.tv_result_locomotive_series)
        private val tvTrainNumber: TextView = itemView.findViewById(R.id.tv_result_train_number)
        private val tvLastWagonNumber: TextView = itemView.findViewById(R.id.tv_result_last_wagon_number)
        private val tvWeight: TextView = itemView.findViewById(R.id.tv_result_weight)
        private val tvAxesTwoAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_two_and_half)
        private val tvAxesThreeAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_three_and_half)
        private val tvAxesFive: TextView = itemView.findViewById(R.id.tv_result_axes_five)
        private val tvAxesSix: TextView = itemView.findViewById(R.id.tv_result_axes_six)
        private val tvAxesSixAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_six_and_half)
        private val tvAxesSeven: TextView = itemView.findViewById(R.id.tv_result_axes_seven)
        private val tvAxesSevenAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_seven_and_half)
        private val tvAxesEight: TextView = itemView.findViewById(R.id.tv_result_axes_eight)
        private val tvAxesEightAndHalf: TextView = itemView.findViewById(R.id.tv_result_axes_eight_and_half)
        private val tvAxesNine: TextView = itemView.findViewById(R.id.tv_result_axes_nine)
        private val tvAxesTen: TextView = itemView.findViewById(R.id.tv_result_axes_ten)
        private val tvAxesTwelve: TextView = itemView.findViewById(R.id.tv_result_axes_twelve)
        private val tvAxesFifteen: TextView = itemView.findViewById(R.id.tv_result_axes_fifteen)
        private val tvPressingPadsTwoAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_two_and_half)
        private val tvPressingPadsThreeAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_three_and_half)
        private val tvPressingPadsFive: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_five)
        private val tvPressingPadsSix: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_six)
        private val tvPressingPadsSixAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_six_and_half)
        private val tvPressingPadsSeven: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_seven)
        private val tvPressingPadsSevenAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_seven_and_half)
        private val tvPressingPadsEight: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_eight)
        private val tvPressingPadsEightAndHalf: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_eight_and_half)
        private val tvPressingPadsNine: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_nine)
        private val tvPressingPadsTen: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_ten)
        private val tvPressingPadsTwelve: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_twelve)
        private val tvPressingPadsFifteen: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_fifteen)
        private val tvTotalAxes: TextView = itemView.findViewById(R.id.tv_result_total_axes)
        private val tvPressingPadsRequired: TextView = itemView.findViewById(R.id.tv_result_pressing_pads_required)
        private val tvHandBrakesRequired: TextView = itemView.findViewById(R.id.tv_result_hand_brakes_required)
        private val llItemTitle: TextView = itemView.findViewById(R.id.tv_saved_certificate_item_title)
        private val clItemBody: ConstraintLayout = itemView.findViewById(R.id.cl_saved_certificate_body)
        private val cvItem: CardView = itemView.findViewById(R.id.cv_item_saved_certificate)

        fun bind(position: Int) {
            val certificateContent = mContentList[position]
            val isExpanded = mExpandedPosition == position
            if (isExpanded) {
                mPreviousExpandedPosition = position
            }
            tvStationStamp.text = certificateContent.stationStamp
            tvIssueTime.text = certificateContent.issueTime
            tvDate.text = certificateContent.date
            tvLocomotiveSeries.text = certificateContent.locomotiveSeries
            tvTrainNumber.text = certificateContent.trainNumber
            tvLastWagonNumber.text = certificateContent.lastWagonNumber
            tvWeight.text = certificateContent.weight
            tvAxesTwoAndHalf.text = certificateContent.axesTwoAndHalf
            tvAxesThreeAndHalf.text = certificateContent.axesThreeAndHalf
            tvAxesFive.text = certificateContent.axesFive
            tvAxesSix.text = certificateContent.axesSix
            tvAxesSixAndHalf.text = certificateContent.axesSixAndHalf
            tvAxesSeven.text = certificateContent.axesSeven
            tvAxesSevenAndHalf.text = certificateContent.axesSevenAndHalf
            tvAxesEight.text = certificateContent.axesEight
            tvAxesEightAndHalf.text = certificateContent.axesEightAndHalf
            tvAxesNine.text = certificateContent.axesNine
            tvAxesTen.text = certificateContent.axesTen
            tvAxesTwelve.text = certificateContent.axesTwelve
            tvAxesFifteen.text = certificateContent.axesFifteen
            tvPressingPadsTwoAndHalf.text = certificateContent.pressingPadsTwoAndHalf
            tvPressingPadsThreeAndHalf.text = certificateContent.pressingPadsThreeAndHalf
            tvPressingPadsFive.text = certificateContent.pressingPadsFive
            tvPressingPadsSix.text = certificateContent.pressingPadsSix
            tvPressingPadsSixAndHalf.text = certificateContent.pressingPadsSixAndHalf
            tvPressingPadsSeven.text = certificateContent.pressingPadsSeven
            tvPressingPadsSevenAndHalf.text = certificateContent.pressingPadsSevenAndHalf
            tvPressingPadsEight.text = certificateContent.pressingPadsEight
            tvPressingPadsEightAndHalf.text = certificateContent.pressingPadsEightAndHalf
            tvPressingPadsNine.text = certificateContent.pressingPadsNine
            tvPressingPadsTen.text = certificateContent.pressingPadsTen
            tvPressingPadsTwelve.text = certificateContent.pressingPadsTwelve
            tvPressingPadsFifteen.text = certificateContent.pressingPadsFifteen
            tvTotalAxes.text = certificateContent.totalAxes
            tvPressingPadsRequired.text = certificateContent.pressingPadsRequired
            tvHandBrakesRequired.text = certificateContent.handBrakesRequired
            clItemBody.visibility = if (isExpanded) View.VISIBLE else View.GONE
            llItemTitle.text = StringBuilder()
                .append(certificateContent.date)
                .append("  ")
                .append(certificateContent.issueTime)
                .toString()
            cvItem.setOnClickListener {
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
            cvItem.setCardBackgroundColor(backgroundColor)
            cvItem.setOnLongClickListener {
                when (mSelectedItemsMap.contains(certificateContent.id)) {
                    true -> mSelectedItemsMap.remove(certificateContent.id)
                    false -> mSelectedItemsMap.add(certificateContent.id)
                }
                val isItemSelected = mSelectedItemsMap.isNotEmpty()
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
    }
}