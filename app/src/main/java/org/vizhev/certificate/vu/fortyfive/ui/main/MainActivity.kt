package org.vizhev.certificate.vu.fortyfive.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.vizhev.certificate.vu.fortyfive.App
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.di.components.DaggerActivityComponent
import org.vizhev.certificate.vu.fortyfive.di.modules.ActivityModule
import org.vizhev.certificate.vu.fortyfive.ui.ViewModelFactory
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mMenu: Menu

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerActivityComponent.builder()
            .applicationComponent((application as App).getApplicationComponent())
            .activityModule(ActivityModule())
            .build()
            .inject(this)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
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

    fun getViewModelFactory(): ViewModelFactory = mViewModelFactory

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