package com.rumbl.rumbl_pt.models

data class HomeScreenHeader(
    val type: HeaderType,
    val title: String
) : IHomeScreenModel

enum class HeaderType {
    REQUESTS, UPCOMING
}