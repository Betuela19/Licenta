package com.masterandroid.BWareMobileApp.onboarding

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkTask : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg urls: String): String {
        val url = URL(urls[0])
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val inputStream = connection.inputStream
        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = StringBuilder()

        reader.use {
            var line = reader.readLine()
            while (line != null) {
                response.append(line)
                line = reader.readLine()
            }
        }

        return response.toString()
    }

    override fun onPostExecute(result: String?) {
        // Use the result in the UI thread

    }
}