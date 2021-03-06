package org.vizhev.certificate.vu.fortyfive.di.components

import dagger.Component
import org.vizhev.certificate.vu.fortyfive.domain.calculator.CertificateCalculator
import org.vizhev.certificate.vu.fortyfive.di.modules.ApplicationModule
import org.vizhev.certificate.vu.fortyfive.domain.Repository
import org.vizhev.certificate.vu.fortyfive.ui.Interactor
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun getRepository(): Repository

    fun getInteractor(): Interactor

    fun getCertificateCalculator(): CertificateCalculator
}