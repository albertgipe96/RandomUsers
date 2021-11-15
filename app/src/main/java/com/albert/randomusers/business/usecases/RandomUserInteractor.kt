package com.albert.randomusers.business.usecases

import com.albert.randomusers.presentation.models.RandomUserUIModel
import com.albert.randomusers.util.Resource

interface RandomUserInteractor {

    suspend fun getRandomUserListUseCase(page: Int, seed: String?): Resource<List<RandomUserUIModel>?>

}