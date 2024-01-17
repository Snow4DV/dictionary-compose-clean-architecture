package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model


import com.google.gson.annotations.SerializedName


data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<String>
)