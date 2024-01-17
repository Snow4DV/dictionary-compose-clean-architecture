package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.presentation.word_lookup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.snowadv.dictionary_compose_clean_architecture.R
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.Definition
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.Meaning
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.model.WordInfo


@Composable
fun WordInfoItem(item: WordInfo, modifier: Modifier = Modifier) {


    Column(
        modifier = modifier.padding(5.dp)
    ) {
        Text(text = item.word, fontSize = 32.sp, color = Color.Black)
        item.phonetic?.let { Text(text = it, fontSize = 15.sp, color = Color.Gray) }
        Spacer(modifier = Modifier.height(2.dp))

        item.meanings.forEachIndexed { i, meaning ->
            Text(
                text = "${i + 1}. ${meaning.partOfSpeech}",
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold
            )

            if(meaning.definitions.isNotEmpty()) Text(
                text = stringResource(R.string.definitions),
                fontSize = 15.sp,
                modifier = modifier.padding(start = 5.dp),
                fontWeight = FontWeight.Bold
            )
            meaning.definitions.forEachIndexed { j, definition ->
                Text(
                    text = "${('a'.code + (j % 26)).toChar()}. ${definition.definition}",
                    fontSize = 15.sp,
                    modifier = modifier.padding(start = 10.dp),
                )
            }
            if(meaning.synonyms.isNotEmpty()) Text(
                text = stringResource(R.string.synonyms),
                fontSize = 15.sp,
                modifier = modifier.padding(start = 5.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = meaning.synonyms.joinToString(", "),
                modifier = modifier.padding(start = 10.dp),
                fontSize = 15.sp
            )
            if(meaning.antonyms.isNotEmpty()) Text(
                text = stringResource(R.string.antonyms),
                fontSize = 15.sp,
                modifier = modifier.padding(start = 5.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = meaning.antonyms.joinToString(", "),
                modifier = modifier.padding(start = 10.dp),
                fontSize = 15.sp,
            )


        }

    }
}


@Preview
@Composable
fun PreviewWordInfoItem() {
    WordInfoItem(
        item = WordInfo(
            "moon",
            "/muːn/",
            listOf(
                Meaning(
                    "proper noun",
                    listOf(
                        Definition(
                            "The Earth's moon Luna; the sole natural satellite of the Earth, represented in astronomy and astrology by ☾.",
                            "look, moon",
                            listOf("round thing", "saturn or something"),
                            listOf("squeary thing", "sun")
                        )
                    ),
                    listOf("round thing", "saturn or something"),
                    listOf("squarey thing", "sun")
                )
            )
        ), Modifier.width(320.dp)
    )
}