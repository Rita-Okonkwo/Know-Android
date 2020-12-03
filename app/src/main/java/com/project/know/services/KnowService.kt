package com.project.know.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.project.know.registration.User
import com.project.know.registration.UserResponse
import com.project.know.ui.questionui.QuestionsItem
import com.project.know.ui.videoui.VideosItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://knowcovid-api-heroku.herokuapp.com/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface KnowApiService {
    @GET("/videos")
    suspend fun getVideos(): List<VideosItem>

    @GET("/questions")
    suspend fun getQuestions(): List<QuestionsItem>

    @POST("/register")
    suspend fun register(@Body user: User) : UserResponse
}

object KnowApi {
    val retrofitService : KnowApiService by lazy {
        retrofit.create(KnowApiService::class.java)
    }
}
