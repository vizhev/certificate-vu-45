package org.vizhev.certificate.vu.fortyfive.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import org.vizhev.certificate.vu.fortyfive.data.AppDataProvider
import org.vizhev.certificate.vu.fortyfive.data.DataProvider
import org.vizhev.certificate.vu.fortyfive.data.api.AppCalculator
import org.vizhev.certificate.vu.fortyfive.data.api.Calculator
import org.vizhev.certificate.vu.fortyfive.data.prefs.AppPreferencesHelper
import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelper
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    /*@Provides
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }*/

    @Provides
    fun providerCalculator(): Calculator {
        return AppCalculator()
    }

    @Provides
    fun providePreferencesHelper(): PreferencesHelper {
        return AppPreferencesHelper(application.baseContext)
    }

    @Provides
    @Singleton
    fun provideDataProvider(calculator: Calculator, preferencesHelper: PreferencesHelper): DataProvider {
        return AppDataProvider(calculator, preferencesHelper)
    }
}