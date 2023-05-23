package com.example.foodieweekly_appv2.model.recipesApi


import com.google.gson.annotations.SerializedName

data class Digest(
    @SerializedName("daily")
    val daily: Double,
    @SerializedName("hasRDI")
    val hasRDI: Boolean,
    @SerializedName("label")
    val label: String,
    @SerializedName("schemaOrgTag")
    val schemaOrgTag: String,
    @SerializedName("sub")
    val sub: List<Sub>,
    @SerializedName("tag")
    val tag: String,
    @SerializedName("total")
    val total: Double,
    @SerializedName("unit")
    val unit: String
)