package com.example.demo.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demo.databinding.ActivityHomeBinding
import com.example.demo.model.PicturesModel
import com.example.demo.util.Utility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setAdapter()
    }

    private fun setAdapter() {
        val jsonFileString = Utility.getJsonDataFromAsset(this)
        val gson = Gson()
        val listType = object : TypeToken<List<PicturesModel>>() {}.type
        val picturesList: List<PicturesModel> = gson.fromJson(jsonFileString, listType)
        binding.imagesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.imagesRecyclerView.adapter = ImagesGridAdapter(picturesList, itemClickListener)
    }

    private val itemClickListener: (View, Int, PicturesModel) -> Unit =
        { view, position, pictureItem ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}