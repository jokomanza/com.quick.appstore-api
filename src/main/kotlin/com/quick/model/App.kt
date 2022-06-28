package com.quick.model

import com.google.gson.annotations.SerializedName

data class App(
    val id: String,
    val name: String,
    @SerializedName("package_name") val packageName: String
)
