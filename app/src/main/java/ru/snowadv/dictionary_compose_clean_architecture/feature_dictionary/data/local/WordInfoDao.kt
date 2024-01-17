package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.DefinitionEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.MeaningEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.WordInfoEntity
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.entity.relations.WordInfoWithMeanings
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo


@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(infos: List<WordInfoEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeanings(meanings: List<MeaningEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDefinitions(definitions: List<DefinitionEntity>): List<Long>



    @Query("DELETE FROM WordInfoEntity WHERE word IN (:words)")
    suspend fun deleteWordInfos(words: List<String>)


    @Transaction
    @Query("SELECT * FROM wordinfoentity WHERE LOWER(word) = LOWER(:word)")
    suspend fun getWordInfo(word: String): List<WordInfoEntity>


    @Transaction
    @Query("SELECT * FROM wordinfoentity WHERE LOWER(word) = LOWER(:word)")
    suspend fun getWordInfosWithMeanings(word: String): List<WordInfoWithMeanings>

     @Transaction
     @Query("SELECT * from wordinfoentity WHERE id = :id")
     suspend fun getWordInfoWithMeanings(id: Int): WordInfoWithMeanings

}