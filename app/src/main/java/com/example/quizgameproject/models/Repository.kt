package com.example.quizgameproject.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Repository() {

    val db = Firebase.firestore
    val TAG="FireBaseDemo"
    private val questions:MutableLiveData<List<Questions>> =MutableLiveData()
    private val questionsList= arrayListOf<Questions>()

    private  val winners= arrayListOf<Users>()

    private val winnerList:MutableLiveData<List<Users>> =MutableLiveData()


    fun addUser(user: Users)
    {
       //val user= hashMapOf("name" to user)
        db.collection("Users").add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "the user added : ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getGeographyQuestions():LiveData<List<Questions>>{
        questionsList.clear()
        db.collection("Topics").document("Geography").collection("quizquestions")
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data.get("question").toString()}")

                    questionsList.add(Questions("",document.data.get("question").toString(),document.data.get("A")
                        .toString(),document.data.get("B").toString(),document.data.get("C").
                    toString(),document.data.get("D").toString() , document.data.get("correctAnswer").toString()))
                        }
                        questions.postValue(questionsList)
                        }


            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        return questions
    }




    fun getUsers():LiveData<List<Users>>{
        winners.clear()
        db.collection("Users").get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    winners.add(Users(document.id,document.data.get("name").toString(),document.data.get("history").toString()))
                }
                winnerList.postValue(winners)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        return winnerList
    }

    fun DeleteData(user: Users) {
        db.collection("Users").document(user.pk).delete()
    }

    fun UpdateUser(user:Users)
    {
        db.collection("Users").document(user.pk).set(user)
        Log.d("Tag","update the data${user.name}")
    }



}