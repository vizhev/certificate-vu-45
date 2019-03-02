package org.vizhev.certificate.vu.fortyfive.di.components

import dagger.Component
import org.vizhev.certificate.vu.fortyfive.data.DataProvider
import org.vizhev.certificate.vu.fortyfive.di.modules.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun getDataProvider(): DataProvider
}