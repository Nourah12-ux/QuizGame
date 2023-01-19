package com.example.quizgameproject.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.quizgameproject.R
import com.example.quizgameproject.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding:ActivityResultBinding
    var username:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
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
                val intent=Intent(this@ResultActivity,QuestionsActivity::class.java)
                startActivity(intent)
                finish()

            }
            back.setOnClickListener{
                val intent=Intent(this@ResultActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}