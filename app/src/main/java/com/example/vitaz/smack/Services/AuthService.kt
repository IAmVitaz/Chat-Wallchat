package com.example.vitaz.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vitaz.smack.Utilities.URL_LOGIN
import com.example.vitaz.smack.Utilities.URL_REGISTER
import org.json.JSONException
import org.json.JSONObject

object AuthService {

    var isLoggedIn = false
    var userEmail = ""
    var authToken = ""

    fun registerUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {

        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        // need JSON to be converted to String to convert it to byte arrray later on (required for Volley request)
        val requestBody = jsonBody.toString()

        //create a request itself
        val registerRequest = object: StringRequest(Method.POST, URL_REGISTER, Response.Listener {response ->
            println(response)
            complete(true)
        }, Response.ErrorListener {error ->
            Log.d("ERROR", "Could not register user: $error")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        //put request in the queue
        Volley.newRequestQueue(context).add(registerRequest)

    }

    fun loginUser(context: Context, email: String, password: String, complete: (Boolean) -> Unit) {

        val jsonBody = JSONObject()
        jsonBody.put("email", email)
        jsonBody.put("password", password)
        // need JSON to be converted to String to convert it to byte arrray later on (required for Volley request)
        val requestBody = jsonBody.toString()

        //create a request itself
        val loginRequest = object: JsonObjectRequest(Method.POST, URL_LOGIN, null, Response.Listener {response ->
            // this is where we parse JSON object
            try {
                authToken = response.getString("token")
                userEmail = response.getString("user")
                isLoggedIn = true
                complete(true)
            } catch (e:JSONException) {
                Log.d("JSON", "EXC:" + e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener {error ->
            // this is where we deal with an error
            Log.d("ERROR", "Could not register user: $error")
            complete(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }

        }

        //put request in the queue
        Volley.newRequestQueue(context).add(loginRequest)


    }

}