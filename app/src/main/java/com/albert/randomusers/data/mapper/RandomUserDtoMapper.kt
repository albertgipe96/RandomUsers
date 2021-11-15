package com.albert.randomusers.data.mapper

import com.albert.randomusers.business.models.RandomUserBusinessModel
import com.albert.randomusers.business.models.RandomUserLocationBusinessModel
import com.albert.randomusers.business.models.RandomUserNameBusinessModel
import com.albert.randomusers.data.models.RandomUserDto

class RandomUserDtoMapper {

    fun mapRandomUserDtoToRandomUserBusinessModel(dtoObject: RandomUserDto): RandomUserBusinessModel {
        return RandomUserBusinessModel(
            id = dtoObject.id?.value,
            name = RandomUserNameBusinessModel(
                first = dtoObject.name?.first,
                last = dtoObject.name?.last
            ),
            email = dtoObject.email,
            pictureThumbnail = dtoObject.picture?.thumbnail,
            phone = dtoObject.phone,
            gender = dtoObject.gender,
            location = RandomUserLocationBusinessModel(
                street = dtoObject.location?.street?.streetName,
                city = dtoObject.location?.city,
                state = dtoObject.location?.state
            ),
            registeredDate = dtoObject.registered?.date
        )
    }

}