package org.vizhev.certificate.vu.fortyfive.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import androidx.viewpager.widget.ViewPager
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
        vp_main.adapter = PagedAdapter(this, supportFragmentManager)
        vp_main.addOnPageChangeListener(createOnPageListener())
    }

    override fun onBackPressed() {
        val isCalculationFragment = supportFragmentManager.fragments[0] is CalculationFragment
        if (isCalculationFragment && isResultViewOpen()) {
            (supportFragmentManager.fragments[0] as CalculationFragment).onBackPressed()
            showMenuAction(vp_main.adapter!!.getPageTitle(0).toString())
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mMenu = menu!!
        showMenuAction(vp_main.adapter!!.getPageTitle(0).toString())
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val fragmentTag = supportFragmentManager.fragments[0].tag
        showMenuAction(fragmentTag!!)
        return true
    }

    fun showMenuAction(fragmentTitle: String) {
        when (fragmentTitle) {
            resources.getString(R.string.calculation_fragment_title) -> {
                mMenu.findItem(R.id.mi_main_save_certificate).isVisible = isResultViewOpen()
                mMenu.findItem(R.id.mi_main_clear_values).isVisible = !isResultViewOpen()
                mMenu.findItem(R.id.mi_main_delete_saved_item).isVisible = false
            }
            resources.getString(R.string.saved_certificates_fragment_title) -> {
                mMenu.findItem(R.id.mi_main_save_certificate).isVisible = false
                mMenu.findItem(R.id.mi_main_clear_values).isVisible = false
                mMenu.findItem(R.id.mi_main_delete_saved_item).isVisible = true
            }
        }
    }

    private fun createOnPageListener(): ViewPager.OnPageChangeListener {
        return object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                showMenuAction(vp_main.adapter!!.getPageTitle(position).toString())
            }
        }
    }

    private fun isResultViewOpen(): Boolean {
        return supportFragmentManager.fragments[0] is CalculationFragment &&
                CalculationFragment.isResultViewOpen
    }
}