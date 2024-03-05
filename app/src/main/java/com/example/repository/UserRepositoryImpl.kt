package com.example.repository

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class UserRepositoryImpl : UserRepositoryLogin {

    override fun userLogin(email: String, password: String): Boolean {
        val URL_LOGIN = URL("http://192.168.1.8/doctor_schedule/login.php")
        val connectionLogin = URL_LOGIN.openConnection() as HttpURLConnection
        connectionLogin.requestMethod = "POST"
        connectionLogin.doOutput = true

        val postData = "email=$email&password=$password"
        val postDataBytes = postData.toByteArray(Charsets.UTF_8)

        connectionLogin.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        connectionLogin.setRequestProperty("Content-Length", postDataBytes.size.toString())

        try {
            val outputStream = DataOutputStream(connectionLogin.outputStream)
            outputStream.write(postDataBytes)
            outputStream.flush()

            val responseCode = connectionLogin.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connectionLogin.inputStream))
                val response = reader.readLine()
                println("Respons dari server: $response")

                val successPattern = "\"success\":true"
                val isSuccess = response.contains(successPattern)

                return isSuccess
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connectionLogin.disconnect()
        }
        return false
    }
}