package org.vizhev.certificate.vu.fortyfive.di.modules

import dagger.Module
import dagger.Provides
import org.vizhev.certificate.vu.fortyfive.di.ActivityScope
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationMvpPresenter
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationMvpView
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationPresenter
import org.vizhev.certificate.vu.fortyfive.ui.main.MainMvpPresenter
import org.vizhev.certificate.vu.fortyfive.ui.main.MainMvpView
import org.vizhev.certificate.vu.fortyfive.ui.main.MainPresenter

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideMainPresenter() : MainMvpPresenter<MainMvpView> {
        return MainPresenter()
    }

    @Provides
    @ActivityScope
    fun provideCalculationPresenter(): CalculationMvpPresenter<CalculationMvpView> {
        return CalculationPresenter()
    }
}