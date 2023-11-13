package com.example.smart_home.domain.model

import androidx.annotation.DrawableRes

data class BottomNavigationItem(
    val route: String,
    val title: String,
    @DrawableRes val selectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)