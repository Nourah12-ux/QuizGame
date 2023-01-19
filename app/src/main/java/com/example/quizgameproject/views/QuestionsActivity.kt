package com.example.quizgameproject.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.quizgameproject.ViewModel.QuizViewModel
import com.example.quizgameproject.databinding.ActivityQuestionsBinding
import com.example.quizgameproject.models.Questions
import com.example.quizgameproject.models.Users
import kotlin.random.Random

class QuestionsActivity : AppCompatActivity() {
    lateinit var binding:ActivityQuestionsBinding
    lateinit var viewModel: QuizViewModel
    var questionList = mutableListOf<Questions>()
    var counter=0
    var listSize = 0
     var countDownTimer:CountDownTimer? = null
     var quest=Questions()
    var AnswerList: ArrayList<Boolean>? =null
    var username:String?=null

        @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        val sharedPref=this.getSharedPreferences("preference_file_key", Context.MODE_PRIVATE)
        username=sharedPref.getString("username",null)


        Log.d("tag","NEW ${counter.toString()}")
        binding.apply {

            next.setOnClickListener {
                if (questionList.size!=0) {
                    val position = Random.nextInt(0, questionList.size)
                    ques.setText(questionList[position].ques.toString())
                    op1.setText(questionList[position].op1.toString())
                    op2.setText(questionList[position].op2.toString())
                    op3.setText(questionList[position].op3.toString())
                    op4.setText(questionList[position].op4.toString())
                    quest=questionList[position]
                    quesCounter.text = String.format("%d/%d",(counter+1),listSize)

                    questionList.removeAt(position)

                    counter++
                    resetColors()


                }

                else{
                    val intent=Intent(this@QuestionsActivity,ResultActivity::class.java)
                    countDownTimer?.cancel()
                        intent.putExtra("TotalMark", TotalMark())
                        intent.putExtra("QuestionSize", listSize)
                        intent.putExtra("message","Congratulations")
                        sharedPref.edit().apply {
                            putInt("TotalMark",TotalMark())
                            putInt("QuestionSize", listSize)
                            apply()
                        }

                    if(TotalMark()>0) {
                        viewModel.UpdateUser(Users(  username.toString(), username.toString(), TotalMark().toString()
                                    )
                                )
                            }


                    startActivity(intent)
                    finish()

                }


            }


            viewModel.getQuestions().observe(this@QuestionsActivity){
               questionList = it as MutableList<Questions>
                listSize = questionList.size

                countDownTimer = object : CountDownTimer((3000*listSize).toLong(),1000) {
                    var time = 0L
                    override fun onTick(p0: Long) {
                        time = p0
                        binding.timer.setText((time/1000).toString())

                    }
                    override fun onFinish()
                    {
                        countDownTimer?.cancel()
                        val intent=Intent(this@QuestionsActivity,TimeOut::class.java)
                         intent.putExtra("TotalMark", TotalMark())
                        intent.putExtra("message","Sorry,Time over ")
                        intent.putExtra("QuestionSize", listSize)
                        startActivity(intent)
                        finish()

                    }
                }

                (countDownTimer as CountDownTimer).start()
                    ques.setText(questionList[0].ques.toString())
                    op1.setText(questionList[0].op1.toString())
                    op2.setText(questionList[0].op2.toString())
                    op3.setText(questionList[0].op3.toString())
                    op4.setText(questionList[0].op4.toString())
                    quest=questionList.get(0)
                quesCounter.text = String.format("%d/%d",(counter+1),listSize)

                AnswerList=ArrayList<Boolean>()
                for(i in 0..listSize){
                    AnswerList!!.add(i,false)
            }

                questionList.removeAt(0)
                counter++

            }
//           println( ques.setText(viewModel.getQuestions().value?.get(3)?.op1))

        }


    }
    fun onClick(view: View) {
        println( "3333333333333333333333333333333333"+quest.ques)
        println("ooooooooooooooooooooooooooooooooooooooooooo")
        binding.apply {
            when (view) {
                op1,op2,op3,op4-> {
                    resetColors()
                    val selected: TextView = view as TextView
                    checkAnswer(selected)

                }
            }

        }
    }
    fun checkAnswer(textView: TextView){

        val selectedAns = textView.text.toString()

        println("ooooooooooooooooooooooooooooooooooooooooo"+quest.ans)
        println("pppppppppppppppppppppppppppppppppp"+selectedAns)

        if(selectedAns == quest.ans)
        {
            AnswerList?.set(counter, true)


        }

        else
        {
            AnswerList?.set(counter, false)

        }
        textView.setTextColor(Color.GRAY)
        textView.isClickable=false

    }
    fun resetColors(){
        binding.apply {
            op1.setTextColor(Color.WHITE)
            op2.setTextColor(Color.WHITE)
            op3.setTextColor(Color.WHITE)
            op4.setTextColor(Color.WHITE)
            op1.isClickable=true
            op2.isClickable=true
            op3.isClickable=true
            op4.isClickable=true
        }
    }
    fun TotalMark():Int{
        var totalMark=0
        for (i in AnswerList!!)
        {
            if(i==true)
                totalMark++
        }
        return totalMark
    }



}