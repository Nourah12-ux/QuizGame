package com.example.quizgameproject.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizgameproject.ViewModel.QuizViewModel
import com.example.quizgameproject.databinding.FragmentProfileBinding
import com.example.quizgameproject.models.Users

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    lateinit var  preferences:SharedPreferences
    lateinit var viewModel: QuizViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        preferences = requireActivity().getSharedPreferences(
            "preference_file_key",
            Context.MODE_PRIVATE
        )
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        var username=preferences.getString("username",null)
        var marks=preferences.getInt("TotalMark",0)
        var questionSize=preferences.getInt("QuestionSize", 0)

        binding.apply {
            textView.setText(username)
            textView3.text=String.format("%d/%d", marks,questionSize)

            imageView3.setOnClickListener {

                preferences.edit().apply {
                   clear()
                    apply()
                }
                 val intent = Intent(requireActivity(), LoginActivity::class.java)
                 startActivity(intent)



            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}