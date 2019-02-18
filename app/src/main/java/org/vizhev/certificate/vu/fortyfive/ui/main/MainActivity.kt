package org.vizhev.certificate.vu.fortyfive.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseActivity
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationFragment

class MainActivity : BaseActivity() {

    private lateinit var mMenu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        view_pager.adapter = PagedAdapter(this, supportFragmentManager)

    }

    override fun onBackPressed() {
        if (isResultViewOpen()) {
            (supportFragmentManager.fragments[0] as CalculationFragment).onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val isCanSave = (supportFragmentManager.fragments[0] is CalculationFragment) &&
                CalculationFragment.isResultViewOpen
        menu!!.findItem(R.id.mi_main_save_certificate).setVisible(isCanSave)
        return true
    }

    private fun isResultViewOpen(): Boolean = supportFragmentManager.fragments[0] is CalculationFragment &&
            CalculationFragment.isResultViewOpen
}