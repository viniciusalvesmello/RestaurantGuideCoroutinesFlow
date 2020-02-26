package io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.injection

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.BuildConfig
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.utils.Constants.CACHE_OFF_LINE_SEVEN_DAYS_IN_SECONDS
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.utils.Constants.CACHE_SIZE_5_MEGA_BYTES
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.utils.Constants.CACHE_TIME_ON_LINE_ONE_HOUR_IN_SECONDS
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.utils.Constants.TIME_OUT_30_SECONDS
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.getStatusConnection
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.ApplicationQualifier
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
class ClientModule {

    @Provides
    fun providesOkHttpClient(
        @ApplicationQualifier context: Context
    ): OkHttpClient {

        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_30_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_30_SECONDS, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, CACHE_SIZE_5_MEGA_BYTES))
            .addInterceptor { chain ->
                val request: Request.Builder = chain.request().newBuilder()

                request.addHeader(
                    "Cache-Control",
                    "public, max-age=$CACHE_TIME_ON_LINE_ONE_HOUR_IN_SECONDS"
                )
                request.addHeader("user-key", BuildConfig.ZomatoApiUserKey)

                return@addInterceptor chain.proceed(request.build())
            }

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        return okHttpClientBuilder.build()
    }
}