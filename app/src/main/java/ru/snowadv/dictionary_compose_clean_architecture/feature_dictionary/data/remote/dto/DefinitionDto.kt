package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.DefinitionEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.Definition


data class DefinitionDto(
    @SerializedName("definition") val definition: String,
    @SerializedName("example") val example: String?,
    @SerializedName("synonyms") val synonyms: List<String>,
    @SerializedName("antonyms") val antonyms: List<String>
) {
    fun toDefinition(): Definition {
        return Definition(
            definition = definition,
            example = example,
            synonyms = synonyms,
            antonyms = antonyms
        )
    }

    fun toDefinitionEntity(meaningId: Long): DefinitionEntity{
        return DefinitionEntity(
            definition = definition,
            example = example,
            synonyms = synonyms,
            antonyms = antonyms,
            meaningId = meaningId
        )
    }
}