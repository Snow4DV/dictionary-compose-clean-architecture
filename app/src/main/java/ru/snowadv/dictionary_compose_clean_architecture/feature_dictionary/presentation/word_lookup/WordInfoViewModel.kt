package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.presentation.word_lookup

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.snowadv.dictionary_compose_clean_architecture.core.util.Resource
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.use_case.GetWordInfo
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun searchForWord(word: String) {
        _searchQuery.value = word
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            getWordInfo(word).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = WordInfoState(
                            words = result.data ?: emptyList(),
                            loading = false,
                        )
                    }
                    is Resource.Error -> {
                        _eventFlow.emit(UiEvent.ShowSnackbar(result.message ?: "Error occurred"))
                        _state.value = WordInfoState(
                            words = result.data ?: emptyList(),
                            loading = false,
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = WordInfoState(
                            words = result.data ?: emptyList(),
                            loading = true,
                        )
                    }
                }

            }.launchIn(this)
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
    }
}