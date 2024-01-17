package ru.snowadv.dictionary_compose_clean_architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.presentation.word_lookup.WordInfoViewModel
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.presentation.word_lookup.WordLookupScreen
import ru.snowadv.dictionary_compose_clean_architecture.ui.theme.DictionarycomposecleanarchitectureTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionarycomposecleanarchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WordLookupScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
