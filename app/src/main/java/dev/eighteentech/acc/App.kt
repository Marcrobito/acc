package dev.eighteentech.acc

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dev.eighteentech.acc.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    private val modules = listOf(apiModule, networkModule, repositoryModule, mainModule, databaseModule)

    companion object{
        lateinit var appContext: Context

        fun hasInternet():Boolean?{
            if(this::appContext.isInitialized) {
                val cm =
                    appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                return activeNetwork?.isConnectedOrConnecting == true
            }
            return null
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
        appContext = this
    }


}