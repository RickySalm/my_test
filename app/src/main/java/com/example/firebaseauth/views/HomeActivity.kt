package com.example.firebaseauth.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauth.R
import com.example.firebaseauth.extensions.Extensions.toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    var pref: SharedPreferences? = null
    var autorization: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_home)
        pref = getSharedPreferences("STATE", Context.MODE_PRIVATE)


        super.onCreate(savedInstanceState)
// sign out a user


        btnSignOut.setOnClickListener {
            val editor = pref?.edit()
            editor?.clear()
            editor?.apply()
            startActivity(Intent(this, SignInActivity::class.java))
            toast("вы вышли из аккаунта")

            finish()
        }
    }
}