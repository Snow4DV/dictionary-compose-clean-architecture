package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class RoomTypeConverter(
    private val jsonConverter: JsonConverter
) {
    @TypeConverter
    fun stringListToJson(list: List<String>): String {
        return jsonConverter.toJson(list) ?: "[]"
    }

    @TypeConverter
    fun stringListFromJson(json: String): List<String> {
        return jsonConverter.fromJson(json, (object : TypeToken<List<String>>() {}).type)
            ?: emptyList()
    }

}