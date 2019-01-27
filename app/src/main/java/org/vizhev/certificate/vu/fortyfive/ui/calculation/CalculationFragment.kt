package org.vizhev.certificate.vu.fortyfive.ui.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calculation.*
import kotlinx.android.synthetic.main.item_result.*
import kotlinx.android.synthetic.main.item_set_data_general.*
import kotlinx.android.synthetic.main.item_set_data_params.*
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseFragment

class CalculationFragment : BaseFragment() {

    private lateinit var mPresenter: CalculationMvpPresenter<CalculationMvpView>

    companion object {
        const val TAG: String = "CalculationFragment"
        var isResultViewOpen: Boolean = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter = getActivityComponent().getCalculationPresenter()
    }

    override fun onStart() {
        super.onStart()
        setViewsVisibility()
        fab_main_calculate.apply {
            setOnClickListener(createOnClickListener())
        }
    }

    fun onBackPressed() {
        isResultViewOpen = false
        setViewsVisibility()
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            isResultViewOpen = true
            setViewsVisibility()
        }
    }

    private fun setViewsVisibility() {
        if (isResultViewOpen) {
            cv_set_data_general.visibility = View.GONE
            cv_set_data_params.visibility = View.GONE
            cv_result.visibility = View.VISIBLE
            fab_main_calculate.hide()
        } else {
            cv_set_data_general.visibility = View.VISIBLE
            cv_set_data_params.visibility = View.VISIBLE
            cv_result.visibility = View.GONE
            fab_main_calculate.show()
        }
    }
}