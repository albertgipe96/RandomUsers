package com.development.feature.randomusers.api.navigation

import kotlinx.serialization.Serializable

sealed interface RandomUsersNavRoute {
    @Serializable data object List : RandomUsersNavRoute
    @Serializable data class Detail(val id: String) : RandomUsersNavRoute
}