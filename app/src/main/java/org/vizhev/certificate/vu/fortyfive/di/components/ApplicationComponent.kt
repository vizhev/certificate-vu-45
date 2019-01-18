package org.vizhev.certificate.vu.fortyfive.di.components

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.vizhev.certificate.vu.fortyfive.di.modules.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    /*@Component.Builder
    interface Builder {

        @BindsInstance
        fun injectApplication(application: Application): Builder

        fun build(): ApplicationComponent
    }*/

    fun getApplicationContext(): Context
}