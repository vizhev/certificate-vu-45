package org.vizhev.certificate.vu.fortyfive.di.components

import dagger.Component
import org.vizhev.certificate.vu.fortyfive.di.ActivityScope
import org.vizhev.certificate.vu.fortyfive.di.modules.ActivityModule
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationMvpPresenter
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationMvpView

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun getCalculationPresenter(): CalculationMvpPresenter<CalculationMvpView>
}