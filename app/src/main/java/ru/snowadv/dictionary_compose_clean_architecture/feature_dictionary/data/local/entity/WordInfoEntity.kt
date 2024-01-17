package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.Meaning
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo


@Entity
class WordInfoEntity(
    @PrimaryKey val id: Long? = null,
    val word: String,
    val phonetic: String?,
)