package com.example.demo.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.demo.R
import com.example.demo.databinding.ActivityDetailBinding
import com.example.demo.model.PicturesModel
import com.example.demo.util.PagerTwoAnimation
import com.example.demo.util.Utility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private var picturesList: List<PicturesModel>? = null
    private var currentScrollPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setBannersAdapter()
        setUpUi()
    }

    private fun setBannersAdapter() {
        currentScrollPosition = intent.getIntExtra("position", 0)
        val jsonFileString = Utility.getJsonDataFromAsset(this)
        val gson = Gson()
        val listType = object : TypeToken<List<PicturesModel>>() {}.type
        picturesList = gson.fromJson(jsonFileString, listType)
        val pagerAdapter = DetailPagerAdapter(this)
        pagerAdapter.setDataList(picturesList)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.setPageTransformer(PagerTwoAnimation())
        binding.viewPager.currentItem = currentScrollPosition
    }

    private fun setUpUi() {
        binding.previousTextView.setOnClickListener {
            currentScrollPosition -= 1
            binding.viewPager.currentItem = currentScrollPosition
            if (currentScrollPosition == 0) {
                binding.previousTextView.visibility = View.GONE
                binding.nextTextView.visibility = View.VISIBLE
            } else {
                binding.previousTextView.visibility = View.VISIBLE
                binding.nextTextView.visibility = View.VISIBLE
            }
        }

        binding.nextTextView.setOnClickListener {
            currentScrollPosition += 1
            binding.viewPager.currentItem = currentScrollPosition
            if (currentScrollPosition == picturesList?.size!! - 1) {
                binding.nextTextView.visibility = View.GONE
                binding.previousTextView.visibility = View.VISIBLE
            } else {
                binding.previousTextView.visibility = View.VISIBLE
                binding.nextTextView.visibility = View.VISIBLE
            }
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentScrollPosition = position
                checkPreviousNextVisibility()
            }
        })
        binding.backImageView.setOnClickListener { onBackPressed() }
    }

    private fun checkPreviousNextVisibility() {
        when {
            picturesList?.size == 1 -> {
                binding.previousTextView.visibility = View.GONE
                binding.nextTextView.visibility = View.GONE
            }
            currentScrollPosition == 0 -> {
                binding.previousTextView.visibility = View.GONE
            }
            currentScrollPosition == picturesList?.size!! - 1 -> {
                binding.nextTextView.visibility = View.GONE
            }
            else -> {
                binding.previousTextView.visibility = View.VISIBLE
                binding.nextTextView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}