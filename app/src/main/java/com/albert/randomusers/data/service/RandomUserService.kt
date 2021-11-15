package com.albert.randomusers.data.service

import com.albert.randomusers.business.models.RandomUserBusinessModel
import com.albert.randomusers.util.Resource

interface RandomUserService {

    suspend fun getRandomUsersList(page: Int, seed: String?): Resource<List<RandomUserBusinessModel>?>

}