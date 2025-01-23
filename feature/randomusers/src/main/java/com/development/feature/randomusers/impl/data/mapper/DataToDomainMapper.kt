package com.development.feature.randomusers.impl.data.mapper

import com.development.core.network.model.RemoteRandomUser
import com.development.core.storage.impl.model.RandomUserEntity
import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.domain.model.RandomUserLocation

class DataToDomainMapper {

    fun RemoteRandomUser.toDomain(): RandomUser {
        return RandomUser(
            id = login.uuid,
            name = name.first,
            surname = name.last,
            email = email,
            picture = picture.medium,
            phone = phone,
            gender = gender,
            location = RandomUserLocation(
                street = "${location.street.name}, ${location.street.number}",
                city = location.city,
                state = location.state
            ),
            registeredDate = registered.date
        )
    }

    fun RandomUserEntity.toDomain(): RandomUser {
        return RandomUser(
            id = id,
            name = name,
            surname = surname,
            email = email,
            picture = picture,
            phone = phone,
            gender = gender,
            location = RandomUserLocation(
                street = street,
                city = city,
                state = state
            ),
            registeredDate = registeredDate
        )
    }

}