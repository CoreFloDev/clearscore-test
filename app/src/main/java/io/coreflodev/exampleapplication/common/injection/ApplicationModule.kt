package io.coreflodev.exampleapplication.common.injection

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.coreflodev.exampleapplication.common.network.ClearScoreApi
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
open class ApplicationModule {

    @Provides
    @Named(SERVER_URL)
    open fun provideServerUrl() = "https://5lfoiyb0b3.execute-api.us-west-2.amazonaws.com/prod/"

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun provideRetrofit(moshi: Moshi, @Named(SERVER_URL) serverUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(serverUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    @Provides
    fun provideTypicodeApi(retrofit: Retrofit): ClearScoreApi =
        retrofit.create(ClearScoreApi::class.java)

    companion object {
        const val SERVER_URL = "SERVER_URL"
    }
}
