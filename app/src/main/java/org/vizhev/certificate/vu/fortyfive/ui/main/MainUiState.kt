package org.vizhev.certificate.vu.fortyfive.ui.main

abstract class MainUiState {

    companion object {
        var isSaveActionVisible: Boolean = false
        var isDeleteActionVisible: Boolean = false
        var isSavedItemSelected: Boolean = false
        var isFabVisible: Boolean = true
        var isResultViewOpen: Boolean = false
    }
}