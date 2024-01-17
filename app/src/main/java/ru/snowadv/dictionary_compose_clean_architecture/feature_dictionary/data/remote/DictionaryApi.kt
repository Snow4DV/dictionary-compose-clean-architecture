package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.dto.WordInfoDto

interface DictionaryApi {
    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(@Path("word") word: String): List<WordInfoDto>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}