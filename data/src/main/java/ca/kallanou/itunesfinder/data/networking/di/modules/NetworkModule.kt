package ca.kallanou.itunesfinder.data.networking.di.modules

import ca.kallanou.itunesfinder.data.BuildConfig
import ca.kallanou.itunesfinder.data.networking.apis.ItunesApi
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule(private val baseUrl: String) {

    @Reusable
    @Provides
    fun provideRetrofit(): Retrofit {

        val clientBuilder = OkHttpClient.Builder()
        val logLevel: HttpLoggingInterceptor.Level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        clientBuilder.addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))

        return Retrofit.Builder()
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ItunesApi {
        return retrofit.create(ItunesApi::class.java)
    }

}
