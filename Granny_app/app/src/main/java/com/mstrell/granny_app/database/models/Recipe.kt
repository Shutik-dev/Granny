package com.mstrell.granny_app.database.models

class Recipe(
    val _id: Int,
    val title: String,
    val img: String,
    val points_am: Int,
    val ing_am: Int,
    val tools_am: Int,
    val likes: Int,
    val tod: String,
    val descr: String
)