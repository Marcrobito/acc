package dev.eighteentech.acc.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.eighteentech.acc.network.Service
import dev.eighteentech.acc.repository.NetworkModel
import dev.eighteentech.acc.repository.Repository
import dev.eighteentech.acc.repository.RepositoryImpl
import dev.eighteentech.acc.room.DB
import dev.eighteentech.acc.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single{  Service.service }
}

val networkModule = module {
    single{ NetworkModel(get())}
}

val databaseModule = module {
    fun provideDatabase(application: Application)
    = Room.databaseBuilder(application, DB::class.java, "Home")
        .fallbackToDestructiveMigration()
        .build()
    fun provideCardDao(database: DB) = database.cardDao()

    single { provideDatabase(androidApplication()) }
    single { provideCardDao(get()) }
}

val repositoryModule = module {
    single<Repository>{ RepositoryImpl(get(), get()) }
}

val mainModule = module {
    viewModel{  MainViewModel(get()) }
}


