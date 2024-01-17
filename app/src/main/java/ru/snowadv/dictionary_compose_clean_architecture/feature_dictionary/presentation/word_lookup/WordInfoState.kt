package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.presentation.word_lookup

import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val words: List<WordInfo> = emptyList(),
    val loading: Boolean = false
)