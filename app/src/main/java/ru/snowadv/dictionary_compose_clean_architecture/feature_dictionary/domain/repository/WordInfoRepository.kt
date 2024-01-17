package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.snowadv.dictionary_compose_clean_architecture.core.util.Resource
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}