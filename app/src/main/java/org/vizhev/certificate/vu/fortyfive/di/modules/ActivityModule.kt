package org.vizhev.certificate.vu.fortyfive.di.modules

import dagger.Module
import dagger.Provides
import org.vizhev.certificate.vu.fortyfive.data.Repository
import org.vizhev.certificate.vu.fortyfive.di.ActivityScope
import org.vizhev.certificate.vu.fortyfive.domain.CertificateCalculator
import org.vizhev.certificate.vu.fortyfive.domain.Interactor
import org.vizhev.certificate.vu.fortyfive.ui.ViewModelFactory

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideViewModelFactory(interactor: Interactor): ViewModelFactory = ViewModelFactory(interactor)
}