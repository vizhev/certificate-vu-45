package org.vizhev.certificate.vu.fortyfive.ui.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_result.*
import kotlinx.android.synthetic.main.item_set_data_general.*
import kotlinx.android.synthetic.main.item_set_data_params.*
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainMvpView {

    private lateinit var mPresenter: MainMvpPresenter<MainMvpView>

    private lateinit var mCvResult: CardView
    private lateinit var mCvGeneral: CardView
    private lateinit var mCvParams: CardView
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mFabCalculateResult: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(R.layout.activity_main)

        mPresenter = getActivityComponent().getMainPresenter()
        mPresenter.onAttach(this)

        val toolbar: Toolbar = toolbar
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_drawer_menu)
        }

        mDrawerLayout = dl_main

        mCvResult = cv_result
        mCvGeneral = cv_set_data_general
        mCvParams = cv_set_data_params

        val onClickListener: View.OnClickListener = createOnClickListener()
        mFabCalculateResult = fab_main_calculate.apply {
            setOnClickListener(onClickListener)
        }
        changeVisibility()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }

    override fun onBackPressed() {
        when {
            mPresenter.isResultViewVisible() -> mPresenter.onCalculateResult()
            mDrawerLayout.isDrawerOpen(GravityCompat.START) -> mDrawerLayout.closeDrawer(GravityCompat.START)
            else -> super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home ->  {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setResult(string: String) {
        runOnUiThread {
            changeVisibility()
        }
    }

    private fun changeVisibility() {
        if (mPresenter.isResultViewVisible()) {
            mFabCalculateResult.hide()
            mCvGeneral.visibility = View.GONE
            mCvParams.visibility = View.GONE
            mCvResult.visibility = View.VISIBLE
        } else {
            mFabCalculateResult.show()
            mCvGeneral.visibility = View.VISIBLE
            mCvParams.visibility = View.VISIBLE
            mCvResult.visibility = View.GONE
        }
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            mPresenter.onCalculateResult()
        }
    }
}