package com.example.quizgameproject.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizgameproject.ViewModel.QuizViewModel
import com.example.quizgameproject.databinding.FragmentLeaderbourdBinding
import com.example.quizgameproject.models.Users

class LeaderbourdFragment : Fragment() {

    private var _binding: FragmentLeaderbourdBinding? = null
    lateinit var rvAdapter: LeaderbourdAdapter
     var userList=ArrayList<Users>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLeaderbourdBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userList= arrayListOf()
        rvAdapter= LeaderbourdAdapter(userList)
        binding.recyclerView.adapter=rvAdapter
        binding.recyclerView.layoutManager= LinearLayoutManager(context)


        val viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        viewModel.getUsers().observe(viewLifecycleOwner) {
            userList.clear()
            userList.addAll(it)
            userList.sortByDescending {  it.history  }
            rvAdapter.update(userList)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}