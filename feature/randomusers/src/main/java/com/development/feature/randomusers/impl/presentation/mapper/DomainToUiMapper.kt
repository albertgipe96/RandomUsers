package com.development.feature.randomusers.impl.presentation.mapper

import com.development.feature.randomusers.impl.domain.model.RandomUser
import com.development.feature.randomusers.impl.presentation.model.RandomUserDetailUi
import com.development.feature.randomusers.impl.presentation.model.RandomUserItemUi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DomainToUiMapper {

    fun RandomUser.toRandomUserItemUi(): RandomUserItemUi {
        return RandomUserItemUi(
            id = id,
            fullName = "$name $surname",
            email = email,
            picture = picture,
            phone = phone
        )
    }

    private val format = DateTimeFormatter.ofPattern("EEEE dd MMMM u HH:mm")

    fun RandomUser.toRandomUserDetailUi(): RandomUserDetailUi {
        return RandomUserDetailUi(
            id = id,
            gender = gender,
            fullName = "$name $surname",
            locationString = "${location.street}, ${location.city}, ${location.state}",
            formattedRegisteredDate = ZonedDateTime.parse(registeredDate).format(format),
            email = email,
            picture = picture
        )
    }

}