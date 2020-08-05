package com.project.know.repository

import com.project.know.services.KnowApi

class KnowRepository{
    val client  = KnowApi.retrofitService
    suspend fun getVideos() = client.getVideos()
    suspend fun getQuestions() = client.getQuestions()
}