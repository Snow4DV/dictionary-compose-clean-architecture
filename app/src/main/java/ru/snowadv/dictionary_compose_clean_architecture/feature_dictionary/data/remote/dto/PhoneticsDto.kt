package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName


data class PhoneticsDto(
    @SerializedName("text") val text: String,
    @SerializedName("audio") val audio: String,
    @SerializedName("sourceUrl") val sourceUrl: String,
    @SerializedName("license") val license: LicenseDto?
)