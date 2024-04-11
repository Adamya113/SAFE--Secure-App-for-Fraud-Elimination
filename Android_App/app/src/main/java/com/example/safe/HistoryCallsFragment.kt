package com.example.safe

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safe.model.HistoryDataItem

class HistoryCallsFragment : Fragment() {

    private lateinit var rvToday : RecyclerView
    private lateinit var rvYesterday : RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_history_calls, container, false)

        var todayList = arrayListOf<HistoryDataItem>(
            HistoryDataItem(R.drawable.veronica,"Veronica",false,"0","Sat, 10:00AM","+8938479838"),
            HistoryDataItem(R.drawable.airtel,"Airtel",true,"80","Sat, 12:00PM","+5938569838"),
            HistoryDataItem(R.drawable.maria,"Maria",true,"64","Sat, 5:00PM","+6575745480")
        )

        var yesterdayList = arrayListOf<HistoryDataItem>(
            HistoryDataItem(R.drawable.samuel,"Samuel",true,"90",",Fri 10:00AM","+8364527837"),
            HistoryDataItem(R.drawable.john,"John",false,"0","Fri, 12:00PM","+5938569838"),
            HistoryDataItem(R.drawable.lottery,"Lottery",true,"99","Fri, 2:00PM","+5888569838"),
            HistoryDataItem(R.drawable.charles,"Charles",false,"0","Fri, 5:00PM","+6575745480")
        )

        rvToday = view.findViewById(R.id.rvToday)
        rvYesterday = view.findViewById(R.id.rvYesterday)

        rvToday.layoutManager = LinearLayoutManager(requireContext())
        rvToday.adapter = HistoryRecyclerViewAdapter(requireContext(),todayList)

        rvYesterday.layoutManager = LinearLayoutManager(requireContext())
        rvYesterday.adapter = HistoryRecyclerViewAdapter(requireContext(),yesterdayList)

        return view

    }
}