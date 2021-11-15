package com.albert.randomusers.data.service

import android.util.Log
import com.albert.randomusers.business.models.RandomUserBusinessModel
import com.albert.randomusers.data.mapper.RandomUserDtoMapper
import com.albert.randomusers.data.service.api.RandomUserApi
import com.albert.randomusers.util.Constants.PAGE_SIZE
import com.albert.randomusers.util.Resource
import java.lang.Exception
import javax.inject.Inject

class RandomUserServiceImpl @Inject constructor(
    private val api: RandomUserApi,
    private val mapper: RandomUserDtoMapper
) : RandomUserService {

    override suspend fun getRandomUsersList(page: Int, seed: String?): Resource<List<RandomUserBusinessModel>?> {
        val response = try {
            api.getRandomUsersList(page = page, results = PAGE_SIZE, seed = seed ?: "abc")
        } catch (e: Exception) {
            return Resource.Error("Error getting data from service.")
        }
        return Resource.Success(response.results?.map { mapper.mapRandomUserDtoToRandomUserBusinessModel(it) })
    }

}