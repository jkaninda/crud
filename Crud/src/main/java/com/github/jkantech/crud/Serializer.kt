package com.github.jkantech.crud

import com.google.gson.GsonBuilder
import java.io.*

class Serializer {
    private val gson =
        GsonBuilder().enableComplexMapKeySerialization().serializeNulls().setPrettyPrinting()
            .create()

    fun <T> deserialize(string2: String?, class_: Class<T>?): T? {
        val `object`: Any
        try {
            val bufferedReader =
                BufferedReader(FileReader(string2) as Reader)
            `object` = gson.fromJson(bufferedReader as Reader, class_)!!
        } catch (exception: Exception) {
            return null
        }
        return `object` as T
    }

    fun <T> deserializeToString(
        string2: String?,
        class_: Class<T>?
    ): String {
        val t: T? = deserialize(string2, class_)
        return gson.toJson(t)
    }

    fun <T> fromJson(string2: String?, class_: Class<T>?): T {
        return gson.fromJson(string2, class_) as T
    }

    private fun <T> serialize(string2: String?, t: T, class_: Class<T>?): Boolean {
        return try {
            val fileWriter = FileWriter(string2)
            fileWriter.write(gson.toJson(t))
            fileWriter.close()
            true
        } catch (iOException: IOException) {
            false
        }
    }

    fun <T> serialize(
        string2: String?,
        string3: String?,
        class_: Class<T>?
    ): Boolean {
        return this.serialize(string2, gson.fromJson(string3, class_), class_)
    }

    fun <T> toJson(t: T): String {
        return gson.toJson(t)
    }
}