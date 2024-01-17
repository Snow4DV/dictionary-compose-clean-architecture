package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto.DefinitionDto
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.Definition

@Entity(
    foreignKeys = [ForeignKey(
        entity = MeaningEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("meaningId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class DefinitionEntity(
    @PrimaryKey val id: Long? = null,
    val definition: String,
    val example: String?,
    val synonyms: List<String>,
    val antonyms: List<String>,
    @ColumnInfo(index = true)
    val meaningId: Long
) {
    fun toDefinition(): Definition {
        return Definition(
            definition = definition,
            example = example,
            synonyms = synonyms,
            antonyms = antonyms
        )
    }

}