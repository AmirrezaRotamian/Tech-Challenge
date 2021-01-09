package com.example.rayakavosh_task.framework

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException

/**
 * a method for geting json file from asset folder as a string
 */
fun getJsonDataFromAsset(@ApplicationContext context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}