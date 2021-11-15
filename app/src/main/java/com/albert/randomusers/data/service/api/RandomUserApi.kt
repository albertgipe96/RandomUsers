package com.albert.randomusers.data.service.api

import com.albert.randomusers.data.models.ResultResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api/")
    suspend fun getRandomUsersList(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String,
    ): ResultResponseDto

}