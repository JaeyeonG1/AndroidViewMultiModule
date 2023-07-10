package com.tambi.core.network.retrofit

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.tambi.core.network.BuildConfig
import com.tambi.core.network.di.NetworkConfig
import com.tambi.core.network.di.ProductNetwork
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

object RetrofitModule {

    @ProductNetwork
    fun provideProductClient(@ProductNetwork networkConfig: NetworkConfig): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        return OkHttpClient.Builder()
            .addInterceptor {
                val origin: Request = it.request()
                val request: Request.Builder = origin.newBuilder().apply {
                    addHeader("Authorization", "Bearer ${networkConfig.token}")
                    networkConfig.headers.forEach { header -> addHeader(header.key, header.value) }
                }
                it.proceed(request.build())
            }
            .addInterceptor(StethoInterceptor())
            .addInterceptor(loggingInterceptor)
            .addInterceptor(OkHttpProfilerInterceptor())
            .build()
    }

    @ProductNetwork
    fun provideRetrofit(
        @ProductNetwork networkConfig: NetworkConfig,
        @ProductNetwork okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(networkConfig.baseUrl)
        .addConverterFactory(converterFactory)
        .build()
}
