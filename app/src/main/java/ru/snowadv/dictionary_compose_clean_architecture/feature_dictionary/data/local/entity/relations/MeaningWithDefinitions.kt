package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.DefinitionEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.MeaningEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto.MeaningDto
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.Meaning


class MeaningWithDefinitions(
    @Embedded val meaning: MeaningEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "meaningId"
    )
    val definitions: List<DefinitionEntity>
) {
    fun toMeaning(): Meaning {
        return Meaning(
            partOfSpeech = meaning.partOfSpeech,
            definitions = definitions.map { it.toDefinition() },
            synonyms = meaning.synonyms,
            antonyms = meaning.antonyms
        )
    }
}