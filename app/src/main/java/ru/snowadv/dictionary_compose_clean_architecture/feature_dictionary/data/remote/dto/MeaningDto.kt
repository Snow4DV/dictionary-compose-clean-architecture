package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.MeaningEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.Meaning


data class MeaningDto(
    @SerializedName("partOfSpeech") val partOfSpeech: String,
    @SerializedName("definitions") val definitions: List<DefinitionDto>,
    @SerializedName("synonyms") val synonyms: List<String>,
    @SerializedName("antonyms") val antonyms: List<String>
) {
    fun toMeaning(): Meaning {
        return Meaning(
            partOfSpeech = partOfSpeech,
            definitions = definitions.map { it.toDefinition() },
            synonyms = synonyms,
            antonyms = antonyms
        )
    }


    fun toMeaningEntity(wordInfoId: Long): MeaningEntity {
        return MeaningEntity(
            partOfSpeech = partOfSpeech,
            synonyms = synonyms,
            antonyms = antonyms,
            wordInfoId = wordInfoId
        )
    }
}