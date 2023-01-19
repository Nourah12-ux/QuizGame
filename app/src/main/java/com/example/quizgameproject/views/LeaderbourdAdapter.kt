package com.example.quizgameproject.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizgameproject.databinding.LeaderbourdItemBinding
import com.example.quizgameproject.models.Users

class LeaderbourdAdapter (private var user: List<Users>):RecyclerView.Adapter<LeaderbourdAdapter.LeaderBoardViewHolder>() {

    class LeaderBoardViewHolder(var binding: LeaderbourdItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardViewHolder {

        return LeaderBoardViewHolder( LeaderbourdItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: LeaderBoardViewHolder, position: Int) {
        val context = holder.itemView.context
        val users = user[position]
        holder.binding.names.text = users.name
        holder.binding.point.text = users.history
        holder.binding.indexs.text= String.format("#%d", position + 1)
        Glide.with(context)
            .load(users.history)
            .into(holder.binding.profile)
    }

    override fun getItemCount(): Int {
        return user.size
    }

    fun update(newuser:List<Users>)
    {
        user=newuser
        notifyDataSetChanged()
    }


}
