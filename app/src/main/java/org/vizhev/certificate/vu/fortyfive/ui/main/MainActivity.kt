package org.vizhev.certificate.vu.fortyfive.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import org.vizhev.certificate.vu.fortyfive.App
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.di.components.DaggerActivityComponent
import org.vizhev.certificate.vu.fortyfive.di.modules.ActivityModule
import org.vizhev.certificate.vu.fortyfive.ui.ViewModelFactory
import org.vizhev.certificate.vu.fortyfive.ui.about.AboutActivity
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mMenu: Menu

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    lateinit var mMainViewModel: MainViewModel

    companion object {
        private var pagerPosition: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent.builder()
                .applicationComponent((application as App).getApplicationComponent())
                .activityModule(ActivityModule())
                .build()
                .inject(this)
        mMainViewModel = ViewModelProviders
            .of(this, mViewModelFactory)
            .get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
        setSupportActionBar(toolbar)
        vp_main.apply {
            adapter = MainPagerAdapter(context, supportFragmentManager)
        }
    }

    override fun onResume() {
        super.onResume()
        vp_main.addOnPageChangeListener(createOnPageListener())
    }

    override fun onBackPressed() {
        if (isSavedCertificatesFragment()) {
            vp_main.currentItem = 0
            return
        }
        if (isResultViewOpen()) {
            (supportFragmentManager.fragments[0] as CalculationFragment).onBackPressed()
            showMenuAction(pagerPosition)
            return
        }
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mMenu = menu!!
        showMenuAction(pagerPosition)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.mi_main_clear_values -> {

                true
            }
            R.id.mi_main_save_certificate -> {
                mMainViewModel.saveCertificate()
                true
            }
            R.id.mi_main_delete_saved_item -> {

                true
            }
            R.id.mi_main_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> false
        }
    }

    fun showMenuAction(position: Int) {
        when (position) {
            0 -> {
                mMenu.findItem(R.id.mi_main_save_certificate).isVisible = isResultViewOpen()
                mMenu.findItem(R.id.mi_main_clear_values).isVisible = !isResultViewOpen()
                mMenu.findItem(R.id.mi_main_delete_saved_item).isVisible = false
            }
            1 -> {
                mMenu.findItem(R.id.mi_main_save_certificate).isVisible = false
                mMenu.findItem(R.id.mi_main_clear_values).isVisible = false
                mMenu.findItem(R.id.mi_main_delete_saved_item).isVisible = true
            }
        }
    }

    fun getMainViewModel(): MainViewModel = mMainViewModel

    private fun createOnPageListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                pagerPosition = position
                showMenuAction(position)
                if (position == 1) {
                    mMainViewModel.loadSavedCertificates()
                }
            }
        }
    }

    private fun isResultViewOpen(): Boolean {
        return pagerPosition == 0 && CalculationFragment.isResultViewOpen
    }

    private fun isSavedCertificatesFragment(): Boolean {
        return pagerPosition == 1
    }
}