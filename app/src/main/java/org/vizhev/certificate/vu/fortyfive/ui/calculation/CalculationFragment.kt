package org.vizhev.certificate.vu.fortyfive.ui.calculation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.domain.models.CertificateContent
import org.vizhev.certificate.vu.fortyfive.ui.main.MainActivity
import org.vizhev.certificate.vu.fortyfive.ui.main.MainUiState
import org.vizhev.certificate.vu.fortyfive.ui.main.MainViewModel

class CalculationFragment : Fragment() {

    private lateinit var mNestedScrollView: NestedScrollView
    private lateinit var mCvSetDataGeneral: CardView
    private lateinit var mCvSetDataParams: CardView
    private lateinit var mCvResult: CardView

    private lateinit var mEtGeneralStationStamp: EditText
    private lateinit var mEtGeneralLocomotiveSeries: EditText
    private lateinit var mEtGeneralTrainNumber: EditText
    private lateinit var mEtGeneralLastWagonNumber: EditText

    private lateinit var mRbLoaded: RadioButton
    private lateinit var mRbEmpty: RadioButton
    private lateinit var mEtParamsWeight: EditText
    private lateinit var mEtParamsSlopeFactor: EditText
    private lateinit var mEtParamsAxesTwoAndHalf: EditText
    private lateinit var mEtParamsAxesThreeAndHalf: EditText
    private lateinit var mEtParamsAxesFive: EditText
    private lateinit var mEtParamsAxesSix: EditText
    private lateinit var mEtParamsAxesSixAndHalf: EditText
    private lateinit var mEtParamsAxesSeven: EditText
    private lateinit var mEtParamsAxesSevenAndHalf: EditText
    private lateinit var mEtParamsAxesEight: EditText
    private lateinit var mEtParamsAxesEightAndHalf: EditText
    private lateinit var mEtParamsAxesNine: EditText
    private lateinit var mEtParamsAxesTen: EditText
    private lateinit var mEtParamsAxesTwelve: EditText
    private lateinit var mEtParamsHandBrakeAxes: EditText
    private lateinit var mEtParamsBrakeNetworkDensity: EditText

    private lateinit var mTvResultStationStamp: TextView
    private lateinit var mTvResultIssueTime: TextView
    private lateinit var mTvResultDate: TextView
    private lateinit var mTvResultLocomotiveSeries: TextView
    private lateinit var mTvResultTrainNumber: TextView
    private lateinit var mTvResultLastWagonNumber: TextView
    private lateinit var mTvResultWeight: TextView
    private lateinit var mTvResultAxesTwoAndHalf: TextView
    private lateinit var mTvResultAxesThreeAndHalf: TextView
    private lateinit var mTvResultAxesFive: TextView
    private lateinit var mTvResultAxesSix: TextView
    private lateinit var mTvResultAxesSixAndHalf: TextView
    private lateinit var mTvResultAxesSeven: TextView
    private lateinit var mTvResultAxesSevenAndHalf: TextView
    private lateinit var mTvResultAxesEight: TextView
    private lateinit var mTvResultAxesEightAndHalf: TextView
    private lateinit var mTvResultAxesNine: TextView
    private lateinit var mTvResultAxesTen: TextView
    private lateinit var mTvResultAxesTwelve: TextView
    private lateinit var mTvResultAxesFifteen: TextView
    private lateinit var mTvResultPressingPadsTwoAndHalf: TextView
    private lateinit var mTvResultPressingPadsThreeAndHalf: TextView
    private lateinit var mTvResultPressingPadsFive: TextView
    private lateinit var mTvResultPressingPadsSix: TextView
    private lateinit var mTvResultPressingPadsSixAndHalf: TextView
    private lateinit var mTvResultPressingPadsSeven: TextView
    private lateinit var mTvResultPressingPadsSevenAndHalf: TextView
    private lateinit var mTvResultPressingPadsEight: TextView
    private lateinit var mTvResultPressingPadsEightAndHalf: TextView
    private lateinit var mTvResultPressingPadsNine: TextView
    private lateinit var mTvResultPressingPadsTen: TextView
    private lateinit var mTvResultPressingPadsTwelve: TextView
    private lateinit var mTvResultPressingPadsFifteen: TextView
    private lateinit var mTvResultTotalAxes: TextView
    private lateinit var mTvResultPressingPadsRequired: TextView
    private lateinit var mTvResultHandBrakesRequired: TextView
    private lateinit var mTvResultHandBrakeAxes: TextView
    private lateinit var mTvResultBrakeNetworkDensity: TextView

    private lateinit var mMainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calculation, container, false)
        initViews(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mMainViewModel = (context as MainActivity).mMainViewModel
        mMainViewModel.getCertificateData().observe(viewLifecycleOwner, createObserver())
    }

    override fun onStart() {
        super.onStart()
        setViewsVisibility()
    }

    fun onBackPressed() {
        MainUiState.isResultViewOpen = false
        setViewsVisibility()
    }

    fun calculateResult(): Boolean {
        if (!mRbLoaded.isChecked && !mRbEmpty.isChecked) {
            mRbLoaded.requestFocus()
            Snackbar.make(view!!, R.string.activity_main_toast_select_type, Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (mEtParamsWeight.text.toString() == "") {
            mEtParamsWeight.requestFocus()
            Snackbar.make(view!!, R.string.activity_main_toast_enter_weight, Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (mEtParamsSlopeFactor.text.toString() == "") {
            mEtParamsSlopeFactor.requestFocus()
            Snackbar.make(view!!, R.string.activity_main_toast_enter_slope_factor, Snackbar.LENGTH_SHORT).show()
            return false
        }
        val certificateContent = getInputData()
        mMainViewModel.calculateResult(certificateContent)
        view!!.postDelayed({
            setViewsVisibility()
            // two same line it is not bug!
            // ScrollView does't scrolling with one line ¯\_(ツ)_/¯
            mNestedScrollView.fullScroll(View.FOCUS_UP)
            mNestedScrollView.fullScroll(View.FOCUS_UP)
        }, 100)
        return true
    }

    private fun getInputData() = CertificateContent(
        stationStamp = mEtGeneralStationStamp.text.toString(),
        locomotiveSeries = mEtGeneralLocomotiveSeries.text.toString(),
        trainNumber = mEtGeneralTrainNumber.text.toString(),
        lastWagonNumber = mEtGeneralLastWagonNumber.text.toString(),
        isLoaded = mRbLoaded.isChecked,
        weight = mEtParamsWeight.text.toString(),
        slopeFactor = mEtParamsSlopeFactor.text.toString(),
        axesTwoAndHalf = mEtParamsAxesTwoAndHalf.text.toString(),
        axesThreeAndHalf = mEtParamsAxesThreeAndHalf.text.toString(),
        axesFive = mEtParamsAxesFive.text.toString(),
        axesSix = mEtParamsAxesSix.text.toString(),
        axesSixAndHalf = mEtParamsAxesSixAndHalf.text.toString(),
        axesSeven = mEtParamsAxesSeven.text.toString(),
        axesSevenAndHalf = mEtParamsAxesSevenAndHalf.text.toString(),
        axesEight = mEtParamsAxesEight.text.toString(),
        axesEightAndHalf = mEtParamsAxesEightAndHalf.text.toString(),
        axesNine = mEtParamsAxesNine.text.toString(),
        axesTen = mEtParamsAxesTen.text.toString(),
        axesTwelve = mEtParamsAxesTwelve.text.toString(),
        handBrakeAxes = mEtParamsHandBrakeAxes.text.toString(),
        brakeNetworkDensity = mEtParamsBrakeNetworkDensity.text.toString()
    )

    private fun createObserver(): Observer<CertificateContent> {
        return Observer {
            mTvResultStationStamp.text = it.stationStamp
            mTvResultIssueTime.text = it.issueTime
            mTvResultDate.text = it.date
            mTvResultLocomotiveSeries.text = it.locomotiveSeries
            mTvResultTrainNumber.text = it.trainNumber
            mTvResultLastWagonNumber.text = it.lastWagonNumber
            mTvResultWeight.text = it.weight
            mTvResultAxesTwoAndHalf.text = it.axesTwoAndHalf
            mTvResultAxesThreeAndHalf.text = it.axesThreeAndHalf
            mTvResultAxesFive.text = it.axesFive
            mTvResultAxesSix.text = it.axesSix
            mTvResultAxesSixAndHalf.text = it.axesSixAndHalf
            mTvResultAxesSeven.text = it.axesSeven
            mTvResultAxesSevenAndHalf.text = it.axesSevenAndHalf
            mTvResultAxesEight.text = it.axesEight
            mTvResultAxesEightAndHalf.text = it.axesEightAndHalf
            mTvResultAxesNine.text = it.axesNine
            mTvResultAxesTen.text = it.axesTen
            mTvResultAxesTwelve.text = it.axesTwelve
            mTvResultAxesFifteen.text = it.axesFifteen
            mTvResultPressingPadsTwoAndHalf.text = it.pressingPadsTwoAndHalf
            mTvResultPressingPadsThreeAndHalf.text = it.pressingPadsThreeAndHalf
            mTvResultPressingPadsFive.text = it.pressingPadsFive
            mTvResultPressingPadsSix.text = it.pressingPadsSix
            mTvResultPressingPadsSixAndHalf.text = it.pressingPadsSixAndHalf
            mTvResultPressingPadsSeven.text = it.pressingPadsSeven
            mTvResultPressingPadsSevenAndHalf.text = it.pressingPadsSevenAndHalf
            mTvResultPressingPadsEight.text = it.pressingPadsEight
            mTvResultPressingPadsEightAndHalf.text = it.pressingPadsEightAndHalf
            mTvResultPressingPadsNine.text = it.pressingPadsNine
            mTvResultPressingPadsTen.text = it.pressingPadsTen
            mTvResultPressingPadsTwelve.text = it.pressingPadsTwelve
            mTvResultPressingPadsFifteen.text = it.pressingPadsFifteen
            mTvResultTotalAxes.text = it.totalAxes
            mTvResultPressingPadsRequired.text = it.pressingPadsRequired
            mTvResultHandBrakesRequired.text = it.handBrakesRequired
            mTvResultHandBrakeAxes.text = it.handBrakeAxes
            mTvResultBrakeNetworkDensity.text = it.brakeNetworkDensity
        }
    }

    private fun setViewsVisibility() {
        when (MainUiState.isResultViewOpen) {
            true -> {
                mCvSetDataGeneral.visibility = View.GONE
                mCvSetDataParams.visibility = View.GONE
                mCvResult.apply {
                    visibility = View.VISIBLE
                    requestFocus()
                }
            }
            false -> {
                mCvSetDataGeneral.visibility = View.VISIBLE
                mCvSetDataParams.visibility = View.VISIBLE
                mCvResult.visibility = View.GONE
            }
        }
    }

    private fun initViews(view: View) {
        mNestedScrollView = view.findViewById(R.id.nsv_calculation)
        mNestedScrollView.apply {
            isSmoothScrollingEnabled = true
        }

        // item_set_data_params
        mCvSetDataGeneral = view.findViewById(R.id.cv_set_data_general)
        mEtGeneralStationStamp = view.findViewById(R.id.et_general_station_stamp)
        mEtGeneralLocomotiveSeries = view.findViewById(R.id.et_general_locomotive_series)
        mEtGeneralTrainNumber = view.findViewById(R.id.et_general_train_number)
        mEtGeneralLastWagonNumber = view.findViewById(R.id.et_general_tail_wagon_number)

        // item_set_data_general
        mRbLoaded = view.findViewById(R.id.rb_params_loaded)
        mRbEmpty = view.findViewById(R.id.rb_params_empty)
        mCvSetDataParams = view.findViewById(R.id.cv_set_data_params)
        mEtParamsWeight = view.findViewById(R.id.et_params_weight)
        mEtParamsSlopeFactor = view.findViewById(R.id.et_params_slope_factor)
        mEtParamsAxesTwoAndHalf = view.findViewById(R.id.et_axes_two_and_half)
        mEtParamsAxesThreeAndHalf = view.findViewById(R.id.et_axes_three_and_half)
        mEtParamsAxesFive = view.findViewById(R.id.et_axes_five)
        mEtParamsAxesSix = view.findViewById(R.id.et_axes_six)
        mEtParamsAxesSixAndHalf = view.findViewById(R.id.et_axes_six_and_half)
        mEtParamsAxesSeven = view.findViewById(R.id.et_axes_seven)
        mEtParamsAxesSevenAndHalf = view.findViewById(R.id.et_axes_seven_and_half)
        mEtParamsAxesEight = view.findViewById(R.id.et_axes_eight)
        mEtParamsAxesEightAndHalf = view.findViewById(R.id.et_axes_eight_and_half)
        mEtParamsAxesNine = view.findViewById(R.id.et_axes_nine)
        mEtParamsAxesTen = view.findViewById(R.id.et_axes_ten)
        mEtParamsAxesTwelve = view.findViewById(R.id.et_axes_twelve)
        mEtParamsHandBrakeAxes = view.findViewById(R.id.et_hand_brake_axles)
        mEtParamsBrakeNetworkDensity = view.findViewById(R.id.et_brake_network_density)

        // item_result
        mCvResult = view.findViewById(R.id.cv_result)
        mTvResultStationStamp = view.findViewById(R.id.tv_result_station_stamp)
        mTvResultIssueTime = view.findViewById(R.id.tv_result_issue_time)
        mTvResultDate = view.findViewById(R.id.tv_result_date)
        mTvResultLocomotiveSeries = view.findViewById(R.id.tv_result_locomotive_series)
        mTvResultTrainNumber = view.findViewById(R.id.tv_result_train_number)
        mTvResultLastWagonNumber = view.findViewById(R.id.tv_result_last_wagon_number)
        mTvResultWeight = view.findViewById(R.id.tv_result_weight)
        mTvResultAxesTwoAndHalf = view.findViewById(R.id.tv_result_axes_two_and_half)
        mTvResultAxesThreeAndHalf = view.findViewById(R.id.tv_result_axes_three_and_half)
        mTvResultAxesFive = view.findViewById(R.id.tv_result_axes_five)
        mTvResultAxesSix = view.findViewById(R.id.tv_result_axes_six)
        mTvResultAxesSixAndHalf = view.findViewById(R.id.tv_result_axes_six_and_half)
        mTvResultAxesSeven = view.findViewById(R.id.tv_result_axes_seven)
        mTvResultAxesSevenAndHalf = view.findViewById(R.id.tv_result_axes_seven_and_half)
        mTvResultAxesEight = view.findViewById(R.id.tv_result_axes_eight)
        mTvResultAxesEightAndHalf = view.findViewById(R.id.tv_result_axes_eight_and_half)
        mTvResultAxesNine = view.findViewById(R.id.tv_result_axes_nine)
        mTvResultAxesTen = view.findViewById(R.id.tv_result_axes_ten)
        mTvResultAxesTwelve = view.findViewById(R.id.tv_result_axes_twelve)
        mTvResultAxesFifteen = view.findViewById(R.id.tv_result_axes_fifteen)
        mTvResultPressingPadsTwoAndHalf = view.findViewById(R.id.tv_result_pressing_pads_two_and_half)
        mTvResultPressingPadsThreeAndHalf = view.findViewById(R.id.tv_result_pressing_pads_three_and_half)
        mTvResultPressingPadsFive = view.findViewById(R.id.tv_result_pressing_pads_five)
        mTvResultPressingPadsSix = view.findViewById(R.id.tv_result_pressing_pads_six)
        mTvResultPressingPadsSixAndHalf = view.findViewById(R.id.tv_result_pressing_pads_six_and_half)
        mTvResultPressingPadsSeven = view.findViewById(R.id.tv_result_pressing_pads_seven)
        mTvResultPressingPadsSevenAndHalf = view.findViewById(R.id.tv_result_pressing_pads_seven_and_half)
        mTvResultPressingPadsEight = view.findViewById(R.id.tv_result_pressing_pads_eight)
        mTvResultPressingPadsEightAndHalf = view.findViewById(R.id.tv_result_pressing_pads_eight_and_half)
        mTvResultPressingPadsNine = view.findViewById(R.id.tv_result_pressing_pads_nine)
        mTvResultPressingPadsTen = view.findViewById(R.id.tv_result_pressing_pads_ten)
        mTvResultPressingPadsTwelve = view.findViewById(R.id.tv_result_pressing_pads_twelve)
        mTvResultPressingPadsFifteen = view.findViewById(R.id.tv_result_pressing_pads_fifteen)
        mTvResultTotalAxes = view.findViewById(R.id.tv_result_total_axes)
        mTvResultPressingPadsRequired = view.findViewById(R.id.tv_result_pressing_pads_required)
        mTvResultHandBrakesRequired = view.findViewById(R.id.tv_result_hand_brakes_required)
        mTvResultHandBrakeAxes = view.findViewById(R.id.tv_result_hand_brake_axles)
        mTvResultBrakeNetworkDensity = view.findViewById(R.id.tv_result_brake_network_density)
    }
}