package com.akanbi.rickandmorty.di

import com.akanbi.rickandmorty.BuildConfig
import com.akanbi.rickandmorty.common.ProviderContext
import com.akanbi.rickandmorty.domain.mapper.CharacterMapper
import com.akanbi.rickandmorty.network.CharacterAPI
import com.akanbi.rickandmorty.network.EpisodeAPI
import com.akanbi.rickandmorty.network.LocationAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = BuildConfig.API_URL

    @Singleton
    @Provides
    fun buildOkHttpClient() =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient
                .Builder()
                .build()
        }

    @Singleton
    @Provides
    fun buildRetrofit(okHttpClient: OkHttpClient, BASE_URL: String) =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun buildCharacterApi(retrofit: Retrofit) = retrofit.create(CharacterAPI::class.java)

    @Provides
    @Singleton
    fun buildLocationApi(retrofit: Retrofit) = retrofit.create(LocationAPI::class.java)

    @Provides
    @Singleton
    fun buildEpisodeApi(retrofit: Retrofit) = retrofit.create(EpisodeAPI::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun bindCharacterMapper() = CharacterMapper()
}

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    fun bindProviderContext() = ProviderContext()
}