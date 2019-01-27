package org.vizhev.certificate.vu.fortyfive.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseActivity
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseFragment
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
            mPresenter.onStartFragmentTransaction(CalculationFragment.TAG, MainPresenter.ADD_FRAGMENT)
        }
    }

    override fun onResume() {
        super.onResume()
        selectDrawerItemAndSetTitle(mPresenter.getCurrentFragmentTag())
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }

    override fun onBackPressed() {
        when {
            isResultViewOpen() -> {
                (supportFragmentManager.fragments[0] as CalculationFragment).onBackPressed()
            }
            isDrawerOpen() -> {
                closeDrawer()
            }
            isHaveFragmentToBackStack() -> {
                val currentFragmentTag: String? = supportFragmentManager.fragments[0].tag
                mPresenter.onStartFragmentTransaction(currentFragmentTag!!, MainPresenter.REMOVE_FRAGMENT)
            }
            else -> super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                openDrawer()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createOnNavigationSelectListener(): NavigationView.OnNavigationItemSelectedListener {
        return NavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawer_item_calculator -> {
                    mPresenter.onStartFragmentTransaction(CalculationFragment.TAG, MainPresenter.REPLACE_FRAGMENT)
                    closeDrawer()
                    true
                }
                R.id.drawer_item_saved_certificates -> {
                    mPresenter.onStartFragmentTransaction(SavedCertificatesFragment.TAG, MainPresenter.REPLACE_FRAGMENT)
                    closeDrawer()
                    true
                }
                else -> false
            }
        }
    }

    override fun startFragmentTransaction(fragmentTag: String, action: String) {
        val fragment: BaseFragment = when (fragmentTag) {
            SavedCertificatesFragment.TAG -> SavedCertificatesFragment()
            else -> CalculationFragment()
        }
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        when (action) {
            MainPresenter.ADD_FRAGMENT -> {
                fragmentTransaction.add(R.id.frame_layout_root, fragment, fragmentTag)
            }
            MainPresenter.REPLACE_FRAGMENT -> {
                val currentFragmentTag = supportFragmentManager.fragments[0].tag
                val isCurrentFragment = fragmentTag == currentFragmentTag
                if (isCurrentFragment) {
                    return
                }
                fragmentTransaction.replace(R.id.frame_layout_root, fragment, fragmentTag)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }
            MainPresenter.REMOVE_FRAGMENT -> {
                fragmentTransaction.replace(R.id.frame_layout_root, fragment, fragmentTag)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            }
        }
        fragmentTransaction.commit()
        selectDrawerItemAndSetTitle(fragmentTag)
    }

    private fun selectDrawerItemAndSetTitle(fragmentTag: String) {
        when(fragmentTag) {
            CalculationFragment.TAG -> {
                navigation_view.setCheckedItem(R.id.drawer_item_calculator)
                toolbar.setTitle(R.string.calculation_fragment_title)
            }
            SavedCertificatesFragment.TAG -> {
                navigation_view.setCheckedItem(R.id.drawer_item_saved_certificates)
                toolbar.setTitle(R.string.saved_certificates_fragment_title)
            }
        }
    }

    private fun isResultViewOpen(): Boolean = supportFragmentManager.fragments[0] is CalculationFragment &&
            CalculationFragment.isResultViewOpen

    private fun isHaveFragmentToBackStack(): Boolean = mPresenter.getBackStackCount() > 1

    private fun isDrawerOpen(): Boolean = drawer.isDrawerOpen(GravityCompat.START)

    private fun openDrawer() = drawer.openDrawer(GravityCompat.START)

    private fun closeDrawer() = drawer.closeDrawer(GravityCompat.START)

}