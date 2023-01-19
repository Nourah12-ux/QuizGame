package com.example.quizgameproject.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.quizgameproject.models.Questions
import com.example.quizgameproject.models.Repository
import com.example.quizgameproject.models.Users

class QuizViewModel(application: Application): AndroidViewModel(application) {
    val repository:Repository= Repository()

    fun addUser(user:Users){
        repository.addUser(user)
    }

    fun getQuestions():LiveData<List<Questions>>{
        return repository.getGeographyQuestions()
    }


    fun getUsers():LiveData<List<Users>>{
        return repository.getUsers()
    }
    fun DeleteData(user: Users) {
        repository.DeleteData(user)
    }

    fun UpdateUser(user:Users){
        repository.UpdateUser(user)
    }
}