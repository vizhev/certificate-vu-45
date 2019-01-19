package org.vizhev.certificate.vu.fortyfive.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainMvpView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: MainMvpPresenter<MainMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = getActivityComponent().getMainPresenter()
        presenter.onAttach(this)
        recyclerView = findViewById(R.id.rv_main_set_data)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = presenter.onCreateAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}