package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model


import com.google.gson.annotations.SerializedName
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.DefinitionEntity


data class Definition(
    val definition: String,
    val example: String?,
    val synonyms: List<String>,
    val antonyms: List<String>
) {
    fun toEntity(meaningId: Long): DefinitionEntity {
        return DefinitionEntity(null, definition, example, synonyms, antonyms, meaningId)
    }
}