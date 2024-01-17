package ru.snowadv.dictionary_compose_clean_architecture.feature_dictionary.data.util

import java.lang.reflect.Type

interface JsonConverter {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T): String?
}