package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.presentation.word_lookup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import ru.snowadv.dictionary_compose_clean_architecture.R


@Composable
fun WordLookupScreen(
    modifier: Modifier = Modifier, viewModel: WordInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is WordInfoViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier,
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(stringResource(R.string.enter_your_word))},
                    value = viewModel.searchQuery.value,
                    onValueChange = { viewModel.searchForWord(it) }
                )
                Spacer(modifier = Modifier.height(5.dp))
                if(state.loading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                } else if(state.words.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(R.string.nothing_here), fontSize = 25.sp)
                    }
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.words.size) {index ->
                        val wordInfo = state.words[index]
                        if(index > 0) {
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        WordInfoItem(item = wordInfo, modifier = Modifier.fillMaxWidth())
                    }
                }


            }
        }
    }
}