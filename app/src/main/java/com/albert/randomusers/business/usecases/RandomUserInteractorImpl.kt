package com.albert.randomusers.business.usecases

import com.albert.randomusers.business.mapper.RandomUserMapper
import com.albert.randomusers.data.service.RandomUserService
import com.albert.randomusers.presentation.models.RandomUserUIModel
import com.albert.randomusers.util.Resource
import java.lang.Exception
import javax.inject.Inject


class RandomUserInteractorImpl @Inject constructor(
    private val service: RandomUserService,
    private val mapper: RandomUserMapper
) : RandomUserInteractor {

    override suspend fun getRandomUserListUseCase(page: Int, seed: String?): Resource<List<RandomUserUIModel>?> {
        val result = try {
            service.getRandomUsersList(page, seed)
        } catch (e: Exception) {
            return Resource.Error("Error getting data from service.")
        }
        return Resource.Success(result.data?.map { mapper.mapRandomUserBusinessModelToRandomUserUIModel(it) })
    }
}