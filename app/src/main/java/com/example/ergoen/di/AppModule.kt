package com.example.ergoen.di

import androidx.room.Room
import com.example.ergoen.BuildConfig
import com.example.ergoen.data.ChargersRepositoryImpl
import com.example.ergoen.data.AuthRepositoryImpl
import com.example.ergoen.data.db.ErgoenDb
import com.example.ergoen.data.db.mapper.DbMapper
import com.example.ergoen.data.db.mapper.DbMapperImpl
import com.example.ergoen.data.network.client.ErgoenApiClient
import com.example.ergoen.data.network.interceptors.HeadersInterceptor
import com.example.ergoen.data.network.interceptors.TokenRequestInterceptor
import com.example.ergoen.data.network.interceptors.UnauthorizedInterceptor
import com.example.ergoen.data.network.mapper.ApiMapper
import com.example.ergoen.data.network.mapper.ApiMapperImpl
import com.example.ergoen.domain.repository.AuthRepository
import com.example.ergoen.domain.repository.ChargersRepository
import com.example.ergoen.utils.DATA_BASE_NAME
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_NAME = "BASE_URL"
private const val BASE_URL = "https://apitest.virta.fi/"
private const val DISPATCHER_MAIN = "dispatcher_main"
private const val DISPATCHER_IO = "dispatcher_io"

val appModule = module {
    single(named(BASE_URL_NAME)) {
        BASE_URL
    }
    single(named(DISPATCHER_MAIN)) { Dispatchers.Main }
    single(named(DISPATCHER_IO)) { Dispatchers.IO }

    // DB
    single {
        Room
            .databaseBuilder(androidContext(), ErgoenDb::class.java, DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single<DbMapper> {
        DbMapperImpl()
    }
    single {
        get<ErgoenDb>().authDao()
    }


    // Network
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        UnauthorizedInterceptor()
    }

    single {
        HeadersInterceptor()
    }

    single {
        TokenRequestInterceptor(get(), get())
    }

    single {
        val client = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
        }
        client.addInterceptor(get<TokenRequestInterceptor>())
        client.addInterceptor(get<HeadersInterceptor>())
        client.addInterceptor(get<UnauthorizedInterceptor>())
        client.build()
    }

    single {
       GsonBuilder().create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(BASE_URL_NAME)))
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }
    single {
        get<Retrofit>().create(ErgoenApiClient::class.java)
    }
    single<ApiMapper> {
        ApiMapperImpl()
    }

    // Repository
    single<AuthRepository> {
        AuthRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
            get(named(DISPATCHER_IO))
        )
    }
    single<ChargersRepository> {
        ChargersRepositoryImpl(
            get(),
            get(),
            get(named(DISPATCHER_IO))
        )
    }
}