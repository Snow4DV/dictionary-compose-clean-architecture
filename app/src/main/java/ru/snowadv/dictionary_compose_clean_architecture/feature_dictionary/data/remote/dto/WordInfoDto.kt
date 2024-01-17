package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto

import com.google.gson.annotations.SerializedName
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.WordInfoEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo


data class WordInfoDto(
    @SerializedName("word") val word: String,
    @SerializedName("phonetic") val phonetic: String,
    @SerializedName("phonetics") val phonetics: ArrayList<PhoneticsDto>,
    @SerializedName("meanings") val meanings: ArrayList<MeaningDto>,
    @SerializedName("license") val license: LicenseDto?,
    @SerializedName("sourceUrls") val sourceUrls: ArrayList<String>

) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            word = word,
            phonetic = phonetic,
            meanings = meanings.map { it.toMeaning() }
        )
    }

    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            word = word,
            phonetic = phonetic
        )
    }



}