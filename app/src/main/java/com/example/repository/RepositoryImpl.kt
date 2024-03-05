package com.example.repository

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RepositoryImpl : UserRepository {
    override fun userRegister(nama: String, email: String, password: String): Boolean {
        val url = URL("http://192.168.1.8/doctor_schedule/register.php")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true

        val postData = "nama=$nama&email=$email&password=$password"
        val postDataBytes = postData.toByteArray(Charsets.UTF_8)

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        connection.setRequestProperty("Content-Length", postDataBytes.size.toString())

        try {
            val outputStream = DataOutputStream(connection.outputStream)
            outputStream.write(postDataBytes)
            outputStream.flush()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.readLine()
                println("Respons dari server: $response")

                val successPattern = "\"success\":true"
                val isSuccess = response.contains(successPattern)

                return isSuccess
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.disconnect()
        }
        return false
    }
}

