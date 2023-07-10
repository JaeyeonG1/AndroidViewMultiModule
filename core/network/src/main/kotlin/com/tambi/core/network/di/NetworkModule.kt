package com.tambi.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tambi.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import javax.inject.Singleton

/**
 * Module for Basic Network Settings
 * eg. Url, Token, [Converter.Factory]
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @ProductNetwork
    fun provideProductNetworkConfig() = NetworkConfig.Product

    private val converterFactoryJson = Json {
        prettyPrint = true
        isLenient = true // Json 큰따옴표 느슨한 체크
        ignoreUnknownKeys = true // Field 값이 없는 경우 무시
        coerceInputValues = true // "null" 이 들어간 경우 Default Argument 대체
        encodeDefaults = true // 요청 시 Field가 DefaultValue or 아닐 경우에 종종 무시 되는 경우 방지
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return converterFactoryJson.asConverterFactory("application/json".toMediaType())
    }
}

enum class NetworkConfig(val baseUrl: String, val token: String, val headers: Map<String, String>) {
    Product(
        BuildConfig.API_URL,
        BuildConfig.API_KEY,
        mapOf("Content-Type" to "application/json; charset=utf-8")
    )
}
