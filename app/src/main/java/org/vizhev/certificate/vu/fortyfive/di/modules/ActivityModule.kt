package org.vizhev.certificate.vu.fortyfive.di.modules

import dagger.Module
import dagger.Provides
import org.vizhev.certificate.vu.fortyfive.di.ActivityScope
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationMvpPresenter
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationMvpView
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationPresenter

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideCalculationPresenter(): CalculationMvpPresenter<CalculationMvpView> {
        return CalculationPresenter()
    }
}