package com.example.quizgameproject.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizgameproject.R
import com.example.quizgameproject.databinding.ActivityResultBinding
import com.example.quizgameproject.databinding.ActivityTimeOutBinding

class TimeOut : AppCompatActivity() {
    lateinit var binding: ActivityTimeOutBinding
    var username:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTimeOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref=this.getSharedPreferences("preference_file_key", Context.MODE_PRIVATE)
        username=sharedPref.getString("username",null)

        val totalMarks = intent.getIntExtra("TotalMark",0)
        val QuestionSize = intent.getIntExtra("QuestionSize",0)
        var message=intent.getStringExtra("message")

        binding.apply {
            textView3.setText(message)
            score.text = String.format("%d/%d", totalMarks,QuestionSize)

            restart.setOnClickListener{
                val intent= Intent(this@TimeOut,QuestionsActivity::class.java)
                startActivity(intent)
                finish()

            }
            back.setOnClickListener{
                val intent= Intent(this@TimeOut,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}