package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.snowadv.dictionary_compose_clean_architecture.core.util.Resource
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.WordInfoDao
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.DictionaryApi
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.repository.WordInfoRepository
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val cachedList = dao.getWordInfosWithMeanings(word).map { it.toWordInfo() }
        emit(Resource.Loading(cachedList))

        try {
            val remoteDtos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteDtos.map { it.word })
            val wordInfosIds = dao.insertWordInfos(remoteDtos.map { it.toWordInfoEntity() })
            remoteDtos.forEachIndexed { wordInfoIndex, wordInfoDto ->
                val meaningsIds = dao.insertMeanings(wordInfoDto.meanings
                    .map { it.toMeaningEntity(wordInfosIds[wordInfoIndex]) })
                wordInfoDto.meanings.forEachIndexed { meaningIndex, meaningDto ->
                    dao.insertDefinitions(meaningDto.definitions
                        .map { it.toDefinitionEntity(meaningsIds[meaningIndex]) })
                }
            }
            emit(Resource.Success(remoteDtos.map { it.toWordInfo() }))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "No internet connection. Check it.",
                data = cachedList
            ))
        } catch(httpException: HttpException) {
            emit(Resource.Error(
                message = if(httpException.code() == 404) "Such word's not found." else "Oops. Something went very wrong.",
                data = cachedList
            ))
            httpException.printStackTrace()
        }
    }
}