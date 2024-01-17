package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    foreignKeys = [ForeignKey(
        entity = WordInfoEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("wordInfoId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MeaningEntity(
    @PrimaryKey val id: Long? = null,
    val partOfSpeech: String,
    val synonyms: List<String>,
    val antonyms: List<String>,
    @ColumnInfo(index = true)
    val wordInfoId: Long
)