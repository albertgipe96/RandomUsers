package com.development.feature.randomusers.impl.data.datasource

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.development.core.storage.api.dao.RandomUserDao
import com.development.core.storage.impl.model.RandomUserEntity
import com.development.feature.randomusers.impl.data.mapper.DataToDomainMapper
import com.development.feature.randomusers.impl.domain.model.RandomUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalRandomUsersDataSource(
    private val randomUserDao: RandomUserDao
) : LocalRandomUsersDataSource {

    override fun getRandomUsers(): PagingSource<Int,RandomUserEntity> {
        return randomUserDao.getRandomUsersList()
    }

    override suspend fun deleteRandomUser(id: Int) {
        randomUserDao.deleteRandomUser(id)
    }

    override suspend fun deleteAll() {
        randomUserDao.clearAll()
    }

    override suspend fun updateRandomUsers(list: List<RandomUser>) {
        randomUserDao.insertAll(list.map { it.toRandomUserEntity() })
    }

    private fun RandomUser.toRandomUserEntity(): RandomUserEntity {
        return RandomUserEntity(
            id = id,
            name = name,
            surname = surname,
            email = email,
            picture = picture,
            phone = phone,
            gender = gender,
            street = location.street,
            city = location.city,
            state = location.state,
            registeredDate = registeredDate
        )
    }
}