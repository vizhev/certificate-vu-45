package org.vizhev.certificate.vu.fortyfive.ui.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_calculation.*
import kotlinx.android.synthetic.main.item_result.*
import kotlinx.android.synthetic.main.item_set_data_general.*
import kotlinx.android.synthetic.main.item_set_data_params.*
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.dataclasses.CertificateData
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseFragment
import org.vizhev.certificate.vu.fortyfive.ui.main.MainActivity
import org.vizhev.certificate.vu.fortyfive.ui.main.MainViewModel

class CalculationFragment : BaseFragment() {

    private lateinit var mMainViewModel: MainViewModel

    companion object {
        const val TAG: String = "CalculationFragment"
        var isResultViewOpen: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mMainViewModel.getCertificateData().observe(
                this, Observer {
            tv_result_date.text =               it.date
            tv_result_locomotive_series.text =  it.locomotiveSeries
            tv_result_train_number.text =       it.trainNumber
            tv_result_last_wagon_number.text =  it.lastWagonNumber

            //from item_params
            tv_result_locomotive_series.text =  it.locomotiveSeries
            tv_result_total_axes.text =         it.totalAxes
            tv_result_weight.text =             it.weight
        }
        )
    }

    override fun onStart() {
        super.onStart()
        setViewsVisibility()
        btn_main_calculate.apply {
            setOnClickListener(createOnClickListener())
        }
    }

    fun onBackPressed() {
        isResultViewOpen = false
        setViewsVisibility()
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val certificateData = CertificateData()
            certificateData.locomotiveSeries = et_general_locomotive_series.text.toString()
            certificateData.trainNumber = et_general_train_number.text.toString()
            certificateData.lastWagonNumber = et_general_tail_wagon_number.text.toString()
            certificateData.weight = et_params_weight.text.toString()
            certificateData.isLoaded = rg_params.checkedRadioButtonId == R.id.rb_params_empty
            mMainViewModel.setCertificateDate(certificateData)
            isResultViewOpen = true
            setViewsVisibility()
            (activity as MainActivity).showMenuAction(resources.getString(R.string.calculation_fragment_title))
        }
    }

    private fun setViewsVisibility() {
        if (isResultViewOpen) {
            sv_calculation.smoothScrollTo(0, 0)
            cv_set_data_general.visibility = View.GONE
            cv_set_data_params.visibility = View.GONE
            btn_main_calculate.visibility = View.GONE
            cv_result.visibility = View.VISIBLE
        } else {
            cv_set_data_general.visibility = View.VISIBLE
            cv_set_data_params.visibility = View.VISIBLE
            btn_main_calculate.visibility = View.VISIBLE
            cv_result.visibility = View.GONE
        }
        sv_calculation.smoothScrollTo(0, 0)
    }
}