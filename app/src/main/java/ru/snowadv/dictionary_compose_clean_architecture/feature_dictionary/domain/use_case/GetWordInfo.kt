package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.snowadv.dictionary_compose_clean_architecture.core.util.Resource
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.repository.WordInfoRepository

class GetWordInfo(
    val repo: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) return flow {  }
        return repo.getWordInfo(word)
    }
}