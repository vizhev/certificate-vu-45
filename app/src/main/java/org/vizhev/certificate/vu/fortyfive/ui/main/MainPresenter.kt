package org.vizhev.certificate.vu.fortyfive.ui.main

import org.vizhev.certificate.vu.fortyfive.ui.base.BasePresenter

class MainPresenter<V : MainMvpView> : BasePresenter<V>(), MainMvpPresenter<V> {

    companion object {
        const val ADD_FRAGMENT = "Add Fragment"
        const val REPLACE_FRAGMENT = "Replace Fragment"
        const val REMOVE_FRAGMENT = "Remove Fragment"
    }

    private lateinit var mCurrentFragmentTag: String
    private val mBackStackSet: MutableSet<String> = mutableSetOf()

    override fun onStartFragmentTransaction(fragmentTag: String, action: String) {
        mCurrentFragmentTag = when(action) {
            REMOVE_FRAGMENT -> {
                mBackStackSet.remove(fragmentTag)
                mBackStackSet.last()
            }
            else -> {
                if (mBackStackSet.contains(fragmentTag)) {
                    mBackStackSet.remove(fragmentTag)
                }
                mBackStackSet.add(fragmentTag)
                fragmentTag
            }
        }
        getView()!!.startFragmentTransaction(mCurrentFragmentTag, action)
    }

    override fun getCurrentFragmentTag(): String = mCurrentFragmentTag

    override fun getBackStackCount(): Int = mBackStackSet.size
}