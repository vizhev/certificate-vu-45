package org.vizhev.certificate.vu.fortyfive.di.components

import dagger.Component
import org.vizhev.certificate.vu.fortyfive.di.ActivityScope
import org.vizhev.certificate.vu.fortyfive.di.modules.ActivityModule
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationMvpPresenter
import org.vizhev.certificate.vu.fortyfive.ui.calculation.CalculationMvpView
import org.vizhev.certificate.vu.fortyfive.ui.main.MainMvpPresenter
import org.vizhev.certificate.vu.fortyfive.ui.main.MainMvpView

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun getMainPresenter(): MainMvpPresenter<MainMvpView>

    fun getCalculationPresenter(): CalculationMvpPresenter<CalculationMvpView>
}