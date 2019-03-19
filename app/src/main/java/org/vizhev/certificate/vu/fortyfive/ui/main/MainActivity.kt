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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainViewModel.getSavedCertificatesAdapter().setOnSelectItemsListener(this)
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
        mSaveCertificateAction.isVisible = MainUiState.isSaveActionVisible
        mDeleteCertificatesAction = menu.findItem(R.id.mi_main_delete_saved_item)
        mDeleteCertificatesAction.isVisible = MainUiState.isDeleteActionVisible
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
                hideDeleteAction()
                true
            }
            R.id.mi_main_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showDeleteAction() {
        mDeleteCertificatesAction.isVisible = true
        MainUiState.isDeleteActionVisible = true
    }

    override fun hideDeleteAction() {
        mDeleteCertificatesAction.isVisible = false
        MainUiState.isDeleteActionVisible = false
    }

    private fun showSavedAction() {
        mSaveCertificateAction.isVisible = true
        MainUiState.isSaveActionVisible = true
    }

    private fun hideSavedAction() {
        mSaveCertificateAction.isVisible = false
        MainUiState.isSaveActionVisible = false
    }

    private fun showFab() {
        val showAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_fab_show)
        mFab.visibility = View.VISIBLE
        mFab.isActivated = true
        mFab.startAnimation(showAnimation)
        MainUiState.isFabVisible = true
    }

    private fun hideFab() {
        val hideAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_fab_hide)
        mFab.startAnimation(hideAnimation)
        mFab.isActivated = false
        mFab.visibility = View.GONE
        MainUiState.isFabVisible = false
    }

    private fun hideKeyboard() {
        val inputMethod = getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(mViewPager.windowToken, 0)
    }

    private fun isCalculationFragmentWithResult(): Boolean {
        return mViewPager.currentItem == MainPagerAdapter.CALCULATION_FRAGMENT &&
                MainUiState.isResultViewOpen
    }

    private fun isSavedCertificatesFragment(): Boolean {
        return mViewPager.currentItem == MainPagerAdapter.SAVED_CERTIFICATES_FRAGMENT
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            if (mViewPager.currentItem == MainPagerAdapter.CALCULATION_FRAGMENT) {
                val fragment = supportFragmentManager.fragments.first()
                val isResultCalculate: Boolean = (fragment as CalculationFragment).calculateResult()
                if (isResultCalculate && !MainUiState.isResultViewOpen) {
                    MainUiState.isResultViewOpen = true
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
                        if (MainUiState.isDeleteActionVisible) {
                            hideDeleteAction()
                        }
                        if (MainUiState.isResultViewOpen) {
                            showSavedAction()
                        } else {
                            showFab()
                        }
                    }
                    MainPagerAdapter.SAVED_CERTIFICATES_FRAGMENT -> {
                        if (MainUiState.isFabVisible) {
                            hideFab()
                        }
                        if (MainUiState.isSaveActionVisible) {
                            hideSavedAction()
                        }
                        if (MainUiState.isSavedItemSelected && !MainUiState.isDeleteActionVisible) {
                            showDeleteAction()
                        }
                    }
                }
            }
        }
    }

    private fun initViews() {
        mToolbar = findViewById(R.id.tb_main)
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
        when (MainUiState.isFabVisible) {
            true -> mFab.visibility = View.VISIBLE
            false -> mFab.visibility = View.GONE
        }
    }
}