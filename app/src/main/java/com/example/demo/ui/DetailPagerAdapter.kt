package com.example.demo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demo.model.PicturesModel

class DetailPagerAdapter(activity: FragmentActivity,
) : FragmentStateAdapter(activity) {
    private var list: List<PicturesModel>? = null
    fun setDataList(list: List<PicturesModel>?) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = DetailFragment()
        fragment.arguments = Bundle().apply {
            putSerializable("data",list!![position])
        }
        return fragment

    }
}
