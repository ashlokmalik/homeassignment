package com.example.demo.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.demo.R
import com.example.demo.databinding.DetailFragmentBinding
import com.example.demo.model.PicturesModel

class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private var data: PicturesModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("data", PicturesModel::class.java) as PicturesModel
        } else {
            arguments?.getSerializable("data") as PicturesModel
        }
        Glide.with(view).load(data?.url).placeholder(R.drawable.placeholder_image)
            .into(binding.imageView)
        binding.titleTextView.text = data?.title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.descriptionTextView.text =
                Html.fromHtml(data?.explanation, Html.FROM_HTML_MODE_LEGACY).trim()
        } else {
            binding.descriptionTextView.text = Html.fromHtml(data?.explanation).trim()
        }

    }
}