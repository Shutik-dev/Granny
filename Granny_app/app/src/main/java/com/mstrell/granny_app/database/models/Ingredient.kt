package com.mstrell.granny_app.database.models

class Ingredient(
    val _id: Int,
    val rec_id: String,
    val name: String,
    val dose: Int,
    val type: String?
)