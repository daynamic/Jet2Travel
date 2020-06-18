package com.akshat.jet2sample.data

import com.akshat.jet2sample.BuildConfig
import com.akshat.jet2sample.model.Response
import com.akshat.jet2sample.model.ResponseItem
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Akshat on 17/06/20.
 */
interface NetworkService {

    @GET("blogs?")
    fun getNews(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int
    ): Single<ResponseItem>


    companion object {
        fun getService(): NetworkService {

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            }

            val clientBuilder = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }
    }


}