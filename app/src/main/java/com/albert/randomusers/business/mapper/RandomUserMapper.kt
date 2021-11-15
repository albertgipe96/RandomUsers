package com.albert.randomusers.business.mapper

import com.albert.randomusers.business.models.RandomUserBusinessModel
import com.albert.randomusers.business.models.RandomUserLocationBusinessModel
import com.albert.randomusers.business.models.RandomUserNameBusinessModel
import com.albert.randomusers.data.models.RandomUserDto
import com.albert.randomusers.presentation.models.RandomUserLocationUIModel
import com.albert.randomusers.presentation.models.RandomUserNameUIModel
import com.albert.randomusers.presentation.models.RandomUserUIModel

class RandomUserMapper {

    fun mapRandomUserBusinessModelToRandomUserUIModel(businessModel: RandomUserBusinessModel): RandomUserUIModel {
        return RandomUserUIModel(
            id = businessModel.id,
            name = RandomUserNameUIModel(
                first = businessModel.name?.first,
                last = businessModel.name?.last
            ),
            email = businessModel.email,
            pictureThumbnail = businessModel.pictureThumbnail,
            phone = businessModel.phone,
            gender = businessModel.gender,
            location = RandomUserLocationUIModel(
                street = businessModel.location?.street,
                city = businessModel.location?.city,
                state = businessModel.location?.state
            ),
            registeredDate = businessModel.registeredDate
        )
    }

}