package org.vizhev.certificate.vu.fortyfive.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import org.vizhev.certificate.vu.fortyfive.data.GeneralRepository
import org.vizhev.certificate.vu.fortyfive.data.Repository
import org.vizhev.certificate.vu.fortyfive.domain.AppCertificateCalculator
import org.vizhev.certificate.vu.fortyfive.domain.CertificateCalculator
import org.vizhev.certificate.vu.fortyfive.data.prefs.AppPreferencesHelper
import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelper
import org.vizhev.certificate.vu.fortyfive.domain.Interactor
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideInteractor(repository: Repository): Interactor {
        return Interactor(repository)
    }

    @Provides
    fun providePreferencesHelper(): PreferencesHelper {
        return AppPreferencesHelper(application.baseContext)
    }

    @Provides
    fun provideRepository(preferencesHelper: PreferencesHelper): Repository {
        return GeneralRepository(preferencesHelper)
    }
}