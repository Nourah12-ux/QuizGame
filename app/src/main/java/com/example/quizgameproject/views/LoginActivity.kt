package com.example.quizgameproject.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.quizgameproject.R
import com.example.quizgameproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    var usernameFlag=false
    var UsernameList= ArrayList<String>()
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnEnter.setOnClickListener {
                var userName=edName.text.toString()

                println("username"+userName)

                if (userName.isNotEmpty()) {
                    println(",,,,,,,,,,,,,,,,,,,,,,,,"+userName)
                    println("oooooooooooooooooooooooooooooooo")
                    println("userlist"+UsernameList)

                    if (!UsernameList.contains(userName)) {
                        UsernameList.add(userName)

                        usernameFlag = true
                        sharedPreferences = super.getSharedPreferences(
                            "preference_file_key",
                            Context.MODE_PRIVATE
                        )
                        sharedPreferences.edit().apply {
                            putString("username", userName)
                            putBoolean("usernameFlag", usernameFlag)
                            apply()
                        }
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("name", userName)
                        startActivity(intent)
                        finish()
                        println("list"+UsernameList)
                    }
                    else{
                        Toast.makeText(this@LoginActivity,"Please enter another username ",Toast.LENGTH_LONG).show() }
                }
                else{ Toast.makeText(this@LoginActivity,"Please enter your username",Toast.LENGTH_LONG).show() }
            }
        }
    }
}