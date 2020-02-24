package org.vizhev.certificate.vu.fortyfive.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import org.vizhev.certificate.vu.fortyfive.data.RepositoryImpl
import org.vizhev.certificate.vu.fortyfive.domain.calculator.CertificateCalculatorImpl
import org.vizhev.certificate.vu.fortyfive.domain.calculator.CertificateCalculator
import org.vizhev.certificate.vu.fortyfive.domain.Repository
import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelperImpl
import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelper
import org.vizhev.certificate.vu.fortyfive.ui.Interactor
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideInteractor(repository: Repository): Interactor {
        return Interactor(repository)
    }

    @Provides
    @Singleton
    fun provideRepository(preferencesHelper: PreferencesHelper, certificateCalculator: CertificateCalculator): Repository {
        return RepositoryImpl(preferencesHelper, certificateCalculator)
    }

    @Provides
    fun providePreferencesHelper(): PreferencesHelper {
        return PreferencesHelperImpl(application.baseContext)
    }

    @Provides
    fun provideCertificateCalculator(): CertificateCalculator {
        return CertificateCalculatorImpl()
    }
}