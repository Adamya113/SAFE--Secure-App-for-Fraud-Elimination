package com.example.safe
import ViewPagerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val image = view.findViewById<ImageView>(R.id.profileImage)

        image.setOnClickListener{
            toMain(it)
        }

        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(HistoryCallsFragment(), "Calls")
        adapter.addFragment(HistoryMessagesFragment(), "Messages")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }
    fun toMain(view : View) {
        startActivity(Intent(requireContext(),MainActivity::class.java))
    }
}
