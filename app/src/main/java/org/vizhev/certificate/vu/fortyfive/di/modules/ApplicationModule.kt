package org.vizhev.certificate.vu.fortyfive.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

}