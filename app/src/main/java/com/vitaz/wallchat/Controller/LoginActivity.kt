package com.vitaz.wallchat.Controller

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.vitaz.wallchat.R
import com.vitaz.wallchat.Services.AuthService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginSpinner.visibility = View.INVISIBLE
    }

    fun loginLoginButtonClicked(view: View) {
        enableSpinner(true)
        val email = loginEmailText.text.toString()
        val password = loginPasswordText.text.toString()
        hideKeyboard()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            AuthService.loginUser(email, password) {loginSuccess ->
                if (loginSuccess) {
                    AuthService.findUserByEmail(this) {findSuccess ->
                        if (findSuccess) {
                            //activity is sending broadcast as part of it
                            enableSpinner(false)
                            finish()
                        } else {
                            errorToast("Something went wrong, please check email")
                        }

                    }
                } else {
                    errorToast("Something went wrong, please check email/password")
                }
            }
        } else {
            errorToast("Please fill in both email and password")
        }
    }

    fun loginCreateUserButtonClicked(view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
        finish()

    }

    fun errorToast(text: String) {
        Toast.makeText(this, text,
                Toast.LENGTH_LONG).show()
        enableSpinner(false)
    }

    fun enableSpinner (enable: Boolean) {
        if (enable) {
            loginSpinner.visibility = View.VISIBLE
        } else {
            loginSpinner.visibility = View.INVISIBLE
        }
        loginLoginButton.isEnabled = !enable
        loginCreateUserButton.isEnabled = !enable
    }

    fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}