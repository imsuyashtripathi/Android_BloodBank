package suyash.tripathi.bloodbank.ui


import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import suyash.tripathi.bloodbank.di.firebaseModule
import suyash.tripathi.bloodbank.di.preferenceModule
import suyash.tripathi.bloodbank.di.viewModelModule


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(
                    firebaseModule,
                    viewModelModule,
                    preferenceModule))
        }
    }
}

