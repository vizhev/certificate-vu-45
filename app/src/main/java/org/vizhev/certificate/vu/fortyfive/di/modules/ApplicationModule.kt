package org.vizhev.certificate.vu.fortyfive.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.vizhev.certificate.vu.fortyfive.data.AppDataProvider
import org.vizhev.certificate.vu.fortyfive.data.DataProvider
import org.vizhev.certificate.vu.fortyfive.data.api.AppCalculator
import org.vizhev.certificate.vu.fortyfive.data.api.Calculator
import org.vizhev.certificate.vu.fortyfive.data.prefs.AppPreferencesHelper
import org.vizhev.certificate.vu.fortyfive.data.prefs.PreferencesHelper

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Provides
    fun providerCalculator(): Calculator {
        return AppCalculator()
    }

    @Provides
    fun providePreferencesHelper(context: Context): PreferencesHelper {
        return AppPreferencesHelper(context)
    }

    @Provides
    fun provideDataProvider(calculator: Calculator, preferencesHelper: PreferencesHelper): DataProvider {
        return AppDataProvider(calculator, preferencesHelper)
    }
}