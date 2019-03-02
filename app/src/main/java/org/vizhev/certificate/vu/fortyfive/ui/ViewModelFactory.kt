package org.vizhev.certificate.vu.fortyfive.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.vizhev.certificate.vu.fortyfive.data.DataProvider
import org.vizhev.certificate.vu.fortyfive.ui.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val dataProvider: DataProvider): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}