package com.example.quizgameproject.models

data class Questions(val pk:String,
                     val ques :String? = null,
                     val op1 : String? = null,
                     val op2 : String? = null,
                     val op3 : String? = null,
                     val op4 : String? = null,
                     val ans : String? = null

)
{
    constructor() : this("","","","","","")
}


