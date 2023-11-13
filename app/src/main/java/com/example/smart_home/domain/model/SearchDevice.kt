package com.example.smart_home.domain.model

import androidx.annotation.DrawableRes

data class SearchDevice(
    @DrawableRes val picture: Int,
    val title: String,
    val deviceType: String,
    var isSelected: Boolean = false
)