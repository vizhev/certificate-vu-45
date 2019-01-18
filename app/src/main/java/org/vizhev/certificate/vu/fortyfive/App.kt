package org.vizhev.certificate.vu.fortyfive

import android.app.Application
import org.vizhev.certificate.vu.fortyfive.di.components.ApplicationComponent
import org.vizhev.certificate.vu.fortyfive.di.components.DaggerApplicationComponent
import org.vizhev.certificate.vu.fortyfive.di.modules.ApplicationModule

class App : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent = applicationComponent
}