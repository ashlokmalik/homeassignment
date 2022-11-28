package com.example.demo.util

import android.content.Context

class Utility {
    companion object {
        fun getJsonDataFromAsset(context: Context): String? {
            val jsonString: String
            try {
                jsonString =
                    context.assets.open("pictures.json").bufferedReader().use { it.readText() }
            } catch (exception: Exception) {
                exception.printStackTrace()
                return null
            }
            return jsonString
        }
    }
}