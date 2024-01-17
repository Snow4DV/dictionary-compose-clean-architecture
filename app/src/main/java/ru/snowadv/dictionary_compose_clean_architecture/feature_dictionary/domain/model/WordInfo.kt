package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model

import com.google.gson.annotations.SerializedName


data class WordInfo(
    val word: String,
    val phonetic: String?,
    val meanings: List<Meaning>
)