package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.WordInfoDao
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.local.WordInfoDatabase
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.remote.DictionaryApi
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.repository.WordInfoRepositoryImpl
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.util.JsonConverter
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.util.JsonConverterImpl
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.util.RoomTypeConverter
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.repository.WordInfoRepository
import ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.domain.use_case.GetWordInfo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }



    @Provides
    @Singleton
    fun provideWordInfoRepository(api: DictionaryApi, dao: WordInfoDao): WordInfoRepository {
        return WordInfoRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideJsonConverter(gson: Gson): JsonConverter {
        return JsonConverterImpl(gson)
    }

    @Provides
    @Singleton
    fun provideTypeConverter(jsonConverter: JsonConverter): RoomTypeConverter {
        return RoomTypeConverter(jsonConverter)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application, converter: RoomTypeConverter): WordInfoDatabase {
        return Room.databaseBuilder(app, WordInfoDatabase::class.java, "dictionary_database")
            .addTypeConverter(converter)
            .build()
    }

    @Provides
    @Singleton
    fun provideWordInfoDao(database: WordInfoDatabase): WordInfoDao {
        return database.dao
    }

    @Provides
    @Singleton
    fun provideApi(): DictionaryApi {
        return Retrofit.Builder().baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}