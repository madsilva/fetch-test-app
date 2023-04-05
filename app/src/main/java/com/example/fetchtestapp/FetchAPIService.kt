package com.example.fetchtestapp

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FetchAPIService {
    @GET("hiring.json")
    suspend fun getData() : List<ListItem>
}

object FetchAPI {
    val retrofitService : FetchAPIService by lazy {
        retrofit.create(FetchAPIService::class.java)
    }
}