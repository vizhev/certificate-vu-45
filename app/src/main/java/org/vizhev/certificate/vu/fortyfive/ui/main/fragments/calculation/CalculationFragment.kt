package org.vizhev.certificate.vu.fortyfive.ui.main.fragments.calculation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.element_result.*
import kotlinx.android.synthetic.main.fragment_calculation.*
import kotlinx.android.synthetic.main.item_result.*
import kotlinx.android.synthetic.main.item_set_data_general.*
import kotlinx.android.synthetic.main.item_set_data_params.*
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateContent
import org.vizhev.certificate.vu.fortyfive.ui.main.MainActivity
import org.vizhev.certificate.vu.fortyfive.ui.main.MainUiState
import org.vizhev.certificate.vu.fortyfive.ui.main.MainViewModel

class CalculationFragment : Fragment() {

    private lateinit var mMainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mMainViewModel = (context as MainActivity).mMainViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        if (!rb_params_loaded.isChecked && !rb_params_empty.isChecked) {
            rb_params_loaded.requestFocus()
            Snackbar.make(view!!, R.string.activity_main_toast_select_type, Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (et_params_weight.text.toString() == "") {
            et_params_weight.requestFocus()
            Snackbar.make(view!!, R.string.activity_main_toast_enter_weight, Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (et_params_slope_factor.text.toString() == "") {
            et_params_slope_factor.requestFocus()
            Snackbar.make(view!!, R.string.activity_main_toast_enter_slope_factor, Snackbar.LENGTH_SHORT).show()
            return false
        }
        val certificateContent = getInputData()
        mMainViewModel.calculateResult(certificateContent)
        view!!.postDelayed({ setViewsVisibility() }, 100)
        return true
    }

    private fun getInputData(): CertificateContent {
        val certificateContent = CertificateContent()
        certificateContent.stationStamp = et_general_station_stamp.text.toString()
        certificateContent.locomotiveSeries = et_general_locomotive_series.text.toString()
        certificateContent.trainNumber = et_general_train_number.text.toString()
        certificateContent.lastWagonNumber = et_general_tail_wagon_number.text.toString()
        certificateContent.isLoaded = rg_params.checkedRadioButtonId == R.id.rb_params_loaded
        certificateContent.weight = et_params_weight.text.toString()
        certificateContent.slopeFactor = et_params_slope_factor.text.toString()
        certificateContent.axesTwoAndHalf = et_axes_two_and_half.text.toString()
        certificateContent.axesThreeAndHalf = et_axes_three_and_half.text.toString()
        certificateContent.axesFive = et_axes_five.text.toString()
        certificateContent.axesSix = et_axes_six.text.toString()
        certificateContent.axesSixAndHalf = et_axes_six_and_half.text.toString()
        certificateContent.axesSeven = et_axes_seven.text.toString()
        certificateContent.axesSevenAndHalf = et_axes_seven_and_half.text.toString()
        certificateContent.axesEight = et_axes_eight.text.toString()
        certificateContent.axesEightAndHalf = et_axes_eight_and_half.text.toString()
        certificateContent.axesNine = et_axes_nine.text.toString()
        certificateContent.axesTen = et_axes_ten.text.toString()
        certificateContent.axesTwelve = et_axes_twelve.text.toString()
        return certificateContent
    }

    private fun createObserver(): Observer<CertificateContent> {
        return Observer {
            tv_result_station_stamp.text = it.stationStamp
            tv_result_issue_time.text = it.issueTime
            tv_result_date.text = it.date
            tv_result_locomotive_series.text = it.locomotiveSeries
            tv_result_train_number.text = it.trainNumber
            tv_result_last_wagon_number.text = it.lastWagonNumber
            tv_result_weight.text = it.weight
            tv_result_axes_two_and_half.text = it.axesTwoAndHalf
            tv_result_axes_three_and_half.text = it.axesThreeAndHalf
            tv_result_axes_five.text = it.axesFive
            tv_result_axes_six.text = it.axesSix
            tv_result_axes_six_and_half.text = it.axesSixAndHalf
            tv_result_axes_seven.text = it.axesSeven
            tv_result_axes_seven_and_half.text = it.axesSevenAndHalf
            tv_result_axes_eight.text = it.axesEight
            tv_result_axes_eight_and_half.text = it.axesEightAndHalf
            tv_result_axes_nine.text = it.axesNine
            tv_result_axes_ten.text = it.axesTen
            tv_result_axes_twelve.text = it.axesTwelve
            tv_result_axes_fifteen.text = it.axesFifteen
            tv_result_pressing_pads_two_and_half.text = it.pressingPadsTwoAndHalf
            tv_result_pressing_pads_three_and_half.text = it.pressingPadsThreeAndHalf
            tv_result_pressing_pads_five.text = it.pressingPadsFive
            tv_result_pressing_pads_six.text = it.pressingPadsSix
            tv_result_pressing_pads_six_and_half.text = it.pressingPadsSixAndHalf
            tv_result_pressing_pads_seven.text = it.pressingPadsSeven
            tv_result_pressing_pads_seven_and_half.text = it.pressingPadsSevenAndHalf
            tv_result_pressing_pads_eight.text = it.pressingPadsEight
            tv_result_pressing_pads_eight_and_half.text = it.pressingPadsEightAndHalf
            tv_result_pressing_pads_nine.text = it.pressingPadsNine
            tv_result_pressing_pads_ten.text = it.pressingPadsTen
            tv_result_pressing_pads_twelve.text = it.pressingPadsTwelve
            tv_result_pressing_pads_fifteen.text = it.pressingPadsFifteen
            tv_result_total_axes.text = it.totalAxes
            tv_result_pressing_pads_required.text = it.pressingPadsRequired
            tv_result_hand_brakes_required.text = it.handBrakesRequired
        }
    }

    private fun setViewsVisibility() {
        when (MainUiState.isResultViewOpen) {
            true -> {
                cv_set_data_general.visibility = View.GONE
                cv_set_data_params.visibility = View.GONE
                cv_result.visibility = View.VISIBLE
                cv_result.requestFocus()
            }
            false -> {
                cv_set_data_general.visibility = View.VISIBLE
                cv_set_data_params.visibility = View.VISIBLE
                cv_result.visibility = View.GONE
            }
        }
        sv_calculation.postDelayed({sv_calculation.smoothScrollTo(0, 0)}, 10)
    }
}