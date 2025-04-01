package com.saiferwp.swplanetviewerdemo.core.api

import com.saiferwp.swplanetviewerdemo.core.BuildConfigFieldsProvider
import com.saiferwp.swplanetviewerdemo.core.MainConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val API_REQUEST_TIME_OUT_SECONDS = 10L

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    internal fun moshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    internal fun buildRemoteApiService(
        buildConfigFieldsProvider: BuildConfigFieldsProvider,
        moshi: Moshi
    ): Api = Retrofit.Builder()
        .client(
            createOkHttpClient(
                provideLoggingInterceptor(
                    buildConfigFieldsProvider
                )
            )
        )
        .baseUrl(MainConfig.API_HOST)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(Api::class.java)
}

private fun createOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(API_REQUEST_TIME_OUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(API_REQUEST_TIME_OUT_SECONDS, TimeUnit.SECONDS)
        .build()
}

private fun provideLoggingInterceptor(
    buildConfigFieldsProvider: BuildConfigFieldsProvider
): HttpLoggingInterceptor =
    HttpLoggingInterceptor()
        .apply {
            level = if (buildConfigFieldsProvider.get().isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
