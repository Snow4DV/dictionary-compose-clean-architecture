package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LicenseDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)