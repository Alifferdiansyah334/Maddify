package com.example.maddify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.maddify.ui.fragment.FragmentTutorial1
import com.example.maddify.ui.fragment.FragmentTutorial2
import com.example.maddify.ui.fragment.FragmentTutorial3

class TutorialAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    private val fragments = listOf(
        FragmentTutorial1(),
        FragmentTutorial2(),
        FragmentTutorial3()
    )


    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}