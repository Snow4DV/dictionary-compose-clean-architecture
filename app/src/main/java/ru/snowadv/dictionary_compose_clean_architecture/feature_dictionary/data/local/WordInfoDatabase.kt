package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.DefinitionEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.MeaningEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.WordInfoEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.relations.MeaningWithDefinitions
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.relations.WordInfoWithMeanings
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.util.RoomTypeConverter


@Database(
    entities = [WordInfoEntity::class, MeaningEntity::class, DefinitionEntity::class],
    version = 2, exportSchema = false
)
@TypeConverters(RoomTypeConverter::class)
abstract class WordInfoDatabase: RoomDatabase() {

    abstract val dao: WordInfoDao
}