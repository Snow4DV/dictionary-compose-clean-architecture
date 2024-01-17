package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.DefinitionEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.MeaningEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.WordInfoEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto.WordInfoDto
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo


class WordInfoWithMeanings(
    @Embedded val wordInfo: WordInfoEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "wordInfoId",
        entity = MeaningEntity::class
    )
    val meanings: List<MeaningWithDefinitions>
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            word = wordInfo.word,
            phonetic = wordInfo.phonetic,
            meanings = meanings.map { it.toMeaning() }
        )
    }

}