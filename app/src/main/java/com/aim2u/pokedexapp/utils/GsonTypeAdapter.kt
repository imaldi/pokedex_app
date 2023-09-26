package com.aim2u.pokedexapp.utils
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class SnakeCaseTypeAdapter<T>(private val gson: Gson, private val delegate: TypeAdapter<T>) : TypeAdapter<T>() {

    override fun write(writer: JsonWriter, value: T) {
        delegate.write(writer, value)
    }

    override fun read(reader: JsonReader): T {
        // Read the JSON data with snake_case keys
        val jsonObject = gson.fromJson<com.google.gson.JsonObject>(reader, com.google.gson.JsonObject::class.java)

        // Convert snake_case keys to camelCase
        val camelCaseJsonObject = com.google.gson.JsonObject()
        for ((key, value) in jsonObject.entrySet()) {
            val camelCaseKey = convertSnakeToCamel(key)
            camelCaseJsonObject.add(camelCaseKey, value)
        }

        // Deserialize the JSON data with camelCase keys
        val camelCaseJsonReader = gson.newJsonReader(camelCaseJsonObject.toString().reader())
        return delegate.read(camelCaseJsonReader)
    }

    private fun convertSnakeToCamel(snakeCase: String): String {
        val parts = snakeCase.split("_")
        val camelCase = StringBuilder(parts[0])
        for (i in 1 until parts.size) {
            camelCase.append(parts[i].capitalize())
        }
        return camelCase.toString()
    }
}