package com.example.firebaseauth.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauth.R
import com.example.firebaseauth.extensions.Extensions.toast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    lateinit var signInEmail: String
    lateinit var signInPassword: String

    var login = "test@mail.ru"
    var password = "qwertyQWERTY1"
    var valid = arrayOf("mail.ru", "yandex.ru", "gmail.ru", "gmail.com")
    var pref: SharedPreferences? = null
    var autorization: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        pref = getSharedPreferences("STATE", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        if(pref?.contains("replacment")!!){
            autorization = pref?.getBoolean("replacment", true)
            if (autorization as Boolean){
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }

        btnSignIn.setOnClickListener {
            signInUser()
        }
    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun signInUser() {
        signInEmail = etSignInEmail.text.toString().trim()
        signInPassword = etSignInPassword.text.toString().trim()
        var check_email = false
        var check_password = false
        var last = signInEmail.substringAfterLast("@")
        var front = signInEmail.substringBeforeLast("@")
        val digit: Boolean = signInPassword.any() {
            it.isDigit()
        }

        if ("@" in signInEmail && last in valid && front.count() > 0){
            check_email = true
        }else{
            toast("неверно введен email")
        }

        var up = false
        for (i in signInPassword){
            if (i.isUpperCase()){
                up = true
                break
            }

        }
        var down = false
        for (i in signInPassword){
            if (i.isLowerCase()){
                down = true
                break
            }

        }
        if (signInPassword.count() >= 6 && digit && up && down){
            check_password = true
        }else{
            toast("неверно введен password")
        }

        if (notEmpty() && check_email && check_password) {

            if (signInEmail == login && signInPassword == password){
                val editor = pref?.edit()
                editor?.putBoolean("replacment", true)
                editor?.apply()
                startActivity(Intent(this, HomeActivity::class.java))
            }else{
                toast("Ваш аккаунт не зарегестрирован")
            }
        }
    }
}