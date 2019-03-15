package org.vizhev.certificate.vu.fortyfive.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.vizhev.certificate.vu.fortyfive.R
import org.vizhev.certificate.vu.fortyfive.ui.main.fragments.calculation.CalculationFragment
import org.vizhev.certificate.vu.fortyfive.ui.main.fragments.savedcertificates.SavedCertificatesFragment

class MainPagerAdapter(private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    companion object {
        const val CALCULATION_FRAGMENT = 0
        const val SAVED_CERTIFICATES_FRAGMENT = 1
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            1 -> SavedCertificatesFragment()
            else -> CalculationFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            1 -> context.resources.getString(R.string.saved_certificates_fragment_title)
            else -> context.resources.getString(R.string.calculation_fragment_title)

        }
    }
}