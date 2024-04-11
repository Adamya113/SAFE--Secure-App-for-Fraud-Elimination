package com.example.safe

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safe.model.HistoryDataItem
import com.example.safe.model.HomeDataItem

class HomeFragment : Fragment() {

    private lateinit var rvRecent : RecyclerView
    private lateinit var rvContacts : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)


        var recentList = arrayListOf<HomeDataItem>(
            HomeDataItem(R.drawable.veronica,"Veronica","+8938479838"),
            HomeDataItem(R.drawable.airtel,"Airtel","+5938569838"),
            HomeDataItem(R.drawable.maria,"Maria","+6575745480")
        )

        var contactList = arrayListOf<HomeDataItem>(
            HomeDataItem(R.drawable.samuel,"Samuel","+8364527837"),
            HomeDataItem(R.drawable.john,"John","+5938569838"),
            HomeDataItem(R.drawable.lottery,"Lottery","+5888569838"),
            HomeDataItem(R.drawable.charles,"Charles","+6575745480"),
            HomeDataItem(R.drawable.veronica,"Veronica","+8938479838"),
            HomeDataItem(R.drawable.john,"Nitin","+8954656538"),
            HomeDataItem(R.drawable.maria,"Maria","+6575745480")
        )

        rvRecent = view.findViewById(R.id.rvRecents)
        rvContacts = view.findViewById(R.id.rvContacts)

        rvRecent.layoutManager = LinearLayoutManager(requireContext())
        rvRecent.adapter = HomeRecyclerViewAdapter(requireContext(),recentList)


        rvContacts.layoutManager = LinearLayoutManager(requireContext())
        rvContacts.adapter = HomeRecyclerViewAdapter(requireContext(),contactList)

        var image = view.findViewById<ImageView>(R.id.profileImage)
        image.setOnClickListener {
            toMain(it)
        }

        return view
    }

    fun toMain(view : View) {
        startActivity(Intent(requireContext(),MainActivity::class.java))
    }

}