package com.mahmoud.mohammed.qwesysandroidtask.DataLayer.Remote

import android.content.Context

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import java.io.File

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Created by ramadan on 11/15/2017.
 */

object RetrofitModule {

    var mBaseUrl = NetworkUtils.Base_WEATHER_URL


    fun provideRetrofit(context: Context): Retrofit {

        val okHttpClient = okHttpClient(loggingInterceptor(), cache(cacheFile(context)))
        val gson = provideGson()
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()
        return retrofit


    }

    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Timber.i(message) }
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }


    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1000 * 1000).toLong()) //10MB Cahe
    }


    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }


    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build()
    }

    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

}
