package org.vizhev.certificate.vu.fortyfive.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseActivity
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationFragment
import org.vizhev.certificate.vu.fortyfive.ui.savedcertificates.SavedCertificatesFragment

class MainActivity : BaseActivity(), MainMvpView {

    private lateinit var mPresenter: MainMvpPresenter<MainMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(R.layout.activity_main)

        mPresenter = getActivityComponent().getMainPresenter()
        mPresenter.onAttach(this)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_drawer_menu)
        }

        navigation_view.apply {
            setNavigationItemSelectedListener(createOnNavigationSelectListener())
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.frame_layout_root, CalculationFragment(), CalculationFragment.TAG)
                    .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }

    override fun onBackPressed() {
        val isResultViewOpen = supportFragmentManager.fragments[0] is CalculationFragment &&
                CalculationFragment.isResultViewOpen
        when {
            isResultViewOpen -> (supportFragmentManager.fragments[0] as CalculationFragment).onBackPressed()
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createOnNavigationSelectListener(): NavigationView.OnNavigationItemSelectedListener {
        return NavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawer_item_calculator -> {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout_root, CalculationFragment(), CalculationFragment.TAG)
                            .commit()
                    true
                }
                R.id.drawer_item_saved_certificates -> {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout_root, SavedCertificatesFragment(), SavedCertificatesFragment.TAG)
                            .commit()
                    true
                }
                else -> false
            }
        }
    }

    override fun startFragmentTransaction() {

    }
}