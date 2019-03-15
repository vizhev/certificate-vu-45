package org.vizhev.certificate.vu.fortyfive.ui.main

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import org.vizhev.certificate.vu.fortyfive.App
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.di.components.DaggerActivityComponent
import org.vizhev.certificate.vu.fortyfive.di.modules.ActivityModule
import org.vizhev.certificate.vu.fortyfive.ui.ViewModelFactory
import org.vizhev.certificate.vu.fortyfive.ui.about.AboutActivity
import org.vizhev.certificate.vu.fortyfive.ui.main.fragments.calculation.CalculationFragment
import org.vizhev.certificate.vu.fortyfive.ui.main.fragments.savedcertificates.SavedCertificatesAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SavedCertificatesAdapter.OnSelectItemsListener {

    private lateinit var mSaveCertificateAction: MenuItem
    private lateinit var mDeleteCertificatesAction: MenuItem
    private lateinit var mToolbar: Toolbar
    private lateinit var mViewPager: ViewPager
    private lateinit var mTabLayout: TabLayout
    private lateinit var mFab: FloatingActionButton

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent.builder()
                .applicationComponent((application as App).getApplicationComponent())
                .activityModule(ActivityModule())
                .build()
                .inject(this)
        mMainViewModel = ViewModelProviders
                .of(this, mViewModelFactory)
                .get(MainViewModel::class.java)
        if (savedInstanceState == null) {
            MainViewModel.UiState.isFabVisible = true
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun onBackPressed() {
        if (isSavedCertificatesFragment()) {
            mViewPager.currentItem = MainPagerAdapter.CALCULATION_FRAGMENT
            return
        }
        if (isCalculationFragmentWithResult()) {
            (supportFragmentManager.fragments.first() as CalculationFragment).onBackPressed()
            hideSavedAction()
            showFab()
            return
        }
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mSaveCertificateAction = menu!!.findItem(R.id.mi_main_save_certificate)
        mSaveCertificateAction.isVisible = MainViewModel.UiState.isSaveActionVisible
        mDeleteCertificatesAction = menu.findItem(R.id.mi_main_delete_saved_item)
        mDeleteCertificatesAction.isVisible = MainViewModel.UiState.isDeleteActionVisible
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item!!.itemId
        return when (itemId) {
            R.id.mi_main_save_certificate -> {
                val isCertificateSaved = mMainViewModel.saveCertificate()
                when (isCertificateSaved) {
                    true -> Snackbar.make(mViewPager, R.string.activity_main_toast_certificate_saved, Snackbar.LENGTH_SHORT).show()
                    false -> Snackbar.make(mViewPager, R.string.activity_main_toast_certificate_not_saved, Snackbar.LENGTH_SHORT).show()
                }
                true
            }
            R.id.mi_main_delete_saved_item -> {
                mMainViewModel.deleteCertificates()
                true
            }
            R.id.mi_main_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*fun setViewsVisibility(position: Int) {
        when (position) {
            MainPagerAdapter.CALCULATION_FRAGMENT -> {
                mSaveCertificateAction.isVisible = isCalculationFragmentWithResult()
                mDeleteCertificatesAction.isVisible = false
                when (isCalculationFragmentWithResult()) {
                    true -> {
                        if (MainViewModel.UiState.isFabVisible) {
                            hideFab()
                            MainViewModel.UiState.isFabVisible = false
                        }
                        hideKeyboard()
                    }
                    false -> {
                        if (MainViewModel.UiState.isFabVisible) {
                            showFab()
                        }
                    }
                }
            }
            MainPagerAdapter.SAVED_CERTIFICATES_FRAGMENT -> {
                mSaveCertificateAction.isVisible = false
                mDeleteCertificatesAction.isVisible = MainViewModel.UiState.isItemSelected
                hideKeyboard()
                if (MainViewModel.UiState.isFabVisible) {
                    hideFab()
                }
            }
        }
    }*/

    override fun showDeleteAction() {
        mDeleteCertificatesAction.isVisible = true
        MainViewModel.UiState.isDeleteActionVisible = true
    }

    override fun hideDeleteAction() {
        mDeleteCertificatesAction.isVisible = false
        MainViewModel.UiState.isDeleteActionVisible = false
    }

    fun showSavedAction() {
        mSaveCertificateAction.isVisible = true
        MainViewModel.UiState.isSaveActionVisible = true
    }

    fun hideSavedAction() {
        mSaveCertificateAction.isVisible = false
        MainViewModel.UiState.isSaveActionVisible = false
    }

    private fun showFab() {
        val showAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_fab_show)
        mFab.visibility = View.VISIBLE
        mFab.isActivated = true
        mFab.startAnimation(showAnimation)
        MainViewModel.UiState.isFabVisible = true
    }

    private fun hideFab() {
        val hideAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_fab_hide)
        mFab.startAnimation(hideAnimation)
        mFab.isActivated = false
        mFab.visibility = View.GONE
        MainViewModel.UiState.isFabVisible = false
    }

    private fun hideKeyboard() {
        val inputMethod = getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(mViewPager.windowToken, 0)
    }

    private fun isCalculationFragmentWithResult(): Boolean {
        return mViewPager.currentItem == MainPagerAdapter.CALCULATION_FRAGMENT &&
                MainViewModel.UiState.isResultViewOpen
    }

    private fun isSavedCertificatesFragment(): Boolean {
        return mViewPager.currentItem == MainPagerAdapter.SAVED_CERTIFICATES_FRAGMENT
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            if (mViewPager.currentItem == MainPagerAdapter.CALCULATION_FRAGMENT) {
                val fragment = supportFragmentManager.fragments.first()
                val isResultCalculate = (fragment as CalculationFragment).calculateResult()
                if (isResultCalculate) {
                    MainViewModel.UiState.isResultViewOpen = true
                    hideKeyboard()
                    hideFab()
                    showSavedAction()
                }
            }
        }
    }

    private fun createOnPageListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    MainPagerAdapter.CALCULATION_FRAGMENT -> {
                        if (MainViewModel.UiState.isSavedItemSelected) {
                            hideDeleteAction()
                        }
                        if (MainViewModel.UiState.isResultViewOpen) {
                            showSavedAction()
                        } else {
                            showFab()
                        }
                    }
                    MainPagerAdapter.SAVED_CERTIFICATES_FRAGMENT -> {
                        if (MainViewModel.UiState.isFabVisible) {
                            hideFab()
                        }
                        if (MainViewModel.UiState.isSaveActionVisible) {
                            hideSavedAction()
                        }
                        if (MainViewModel.UiState.isSavedItemSelected && !MainViewModel.UiState.isDeleteActionVisible) {
                            showDeleteAction()
                        }
                    }
                }
            }
        }
    }

    private fun initViews() {
        mToolbar = findViewById(R.id.tb_main)
        mToolbar.inflateMenu(R.menu.main_menu)
        setSupportActionBar(mToolbar)
        mViewPager = findViewById(R.id.vp_main)
        mViewPager.apply {
            adapter = MainPagerAdapter(context, supportFragmentManager)
            addOnPageChangeListener(createOnPageListener())
        }
        mTabLayout = findViewById(R.id.tl_main)
        mTabLayout.apply {
            setupWithViewPager(mViewPager)
        }
        mFab = findViewById(R.id.fab_main)
        mFab.apply {
            bringToFront()
            setOnClickListener(createOnClickListener())
        }
        when (MainViewModel.UiState.isFabVisible) {
            true -> mFab.visibility = View.VISIBLE
            false -> mFab.visibility = View.GONE
        }
    }
}