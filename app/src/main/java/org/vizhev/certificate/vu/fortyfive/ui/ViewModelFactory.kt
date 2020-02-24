package org.vizhev.certificate.vu.fortyfive.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.vizhev.certificate.vu.fortyfive.ui.main.MainViewModel

class ViewModelFactory(
    private val interactor: Interactor
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}