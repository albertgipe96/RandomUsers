package com.development.core.network.service

import com.development.core.network.model.RemoteRandomUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersApiService {

    @GET("/api")
    suspend fun getRandomUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String
    ) : RemoteRandomUsers

}