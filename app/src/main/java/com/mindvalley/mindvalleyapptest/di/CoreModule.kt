package com.mindvalley.mindvalleyapptest.di

import androidx.room.Room
import com.mindvalley.mindvalleyapptest.BuildConfig
import com.mindvalley.mindvalleyapptest.data.local.LocalDataSource
import com.mindvalley.mindvalleyapptest.data.local.room.ChannelDatabase
import com.mindvalley.mindvalleyapptest.data.remote.RemoteDataSource
import com.mindvalley.mindvalleyapptest.data.remote.network.ApiService
import com.mindvalley.mindvalleyapptest.data.repository.ChannelRepo
import com.mindvalley.mindvalleyapptest.data.util.INetworkUtil
import com.mindvalley.mindvalleyapptest.data.util.NetworkUtil
import com.mindvalley.mindvalleyapptest.domain.repository.IChannelRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single<IChannelRepo> {
        ChannelRepo(
            get(), get(), androidContext()
        )
    }
}


val databaseModule = module {

    factory { get<ChannelDatabase>().channelDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ChannelDatabase::class.java, "album.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val utilModule = module {
    single<INetworkUtil> { NetworkUtil() }
}
