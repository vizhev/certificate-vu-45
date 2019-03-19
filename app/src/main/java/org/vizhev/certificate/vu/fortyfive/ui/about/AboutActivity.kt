package org.vizhev.certificate.vu.fortyfive.ui.about

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import org.vizhev.certificate.vu.fortyfive.R

class AboutActivity : AppCompatActivity() {

    private lateinit var mListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        mListView = findViewById(R.id.lv_about)
        val headerView = layoutInflater.inflate(R.layout.element_header_about, mListView, false)
        mListView.apply {
            addHeaderView(headerView)
            setHeaderDividersEnabled(true)
            setSelectionAfterHeaderView()
            adapter = createAdapter()
            setOnItemClickListener { parent, view, position, id ->
                when (position) {
                    1 -> {

                    }
                }
            }
        }
    }

    private fun createAdapter(): SimpleAdapter {
        val dataLIst = mutableListOf<Map<String, String>>()
        val authorMap = mutableMapOf<String, String>()

        dataLIst.add(authorMap)
        return SimpleAdapter(
                this,
                dataLIst,
                android.R.layout.simple_list_item_2,
                arrayOf("First line", "Second line"),
                intArrayOf(android.R.id.text1, android.R.id.text2)
        )
    }
}