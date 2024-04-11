package com.example.safe

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.safe.databinding.HistoryContactItemLayoutBinding
import com.example.safe.model.HistoryDataItem

class HistoryRecyclerViewAdapter(var context : Context, var list : ArrayList<HistoryDataItem>) : RecyclerView.Adapter<HistoryRecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(var binding : HistoryContactItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        var card = binding.cardView
        var image = binding.imgContactImage
        var name = binding.txtContactName
        var phone = binding.txtPhoneNumber
        var spam = binding.txtSpam
        var percentage = binding.txtSpamPercentage
        var dayTime = binding.txtDayTime

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = HistoryContactItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.image.setImageResource(list[position].image)
        holder.name.text = list[position].name
        holder.phone.text = list[position].phone
        if (list[position].spam) {
            holder.card.setBackgroundColor(Color.argb(57,255,174,174))
            holder.spam.visibility = View.VISIBLE
            holder.percentage.visibility = View.VISIBLE
            holder.percentage.text = list[position].percentage+"%"
        } else {
            holder.spam.visibility = View.INVISIBLE
            holder.percentage.visibility = View.INVISIBLE
        }
        holder.dayTime.text = list[position].date
    }

    override fun getItemCount(): Int {
        return list.size
    }

}