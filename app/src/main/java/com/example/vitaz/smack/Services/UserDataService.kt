package com.example.vitaz.smack.Services

import android.graphics.Color
import java.util.*

object UserDataService {

    var id = ""
    var avatarColor = ""
    var avatarName = ""
    var name = ""
    var email = ""

    fun logout() {
        id = ""
        avatarColor = ""
        avatarName = ""
        name = ""
        email = ""
        AuthService.authToken = ""
        AuthService.userEmail = ""
        AuthService.isLoggedIn = false
    }

    fun returnAvatarColor(componens: String) : Int {
        // [0.42745098039215684, 0.6078431372549019, 0.2627450980392157, 1]
        // 0.42745098039215684 0.6078431372549019 0.2627450980392157 1

        val strippedColor = componens
                .replace("[","")
                .replace("]","")
                .replace(",","")

        var r = 0
        var g = 0
        var b = 0

        val scanner = Scanner(strippedColor)
        if (scanner.hasNext()) {
            r = (scanner.nextDouble() * 255).toInt()
            g = (scanner.nextDouble() * 255).toInt()
            b = (scanner.nextDouble() * 255).toInt()
        }

        return Color.rgb(r,g,b)
    }




}