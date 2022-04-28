package com.example.map.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.map.BuildConfig
import com.example.map.R
import com.example.map.databinding.FragmentAboutBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private lateinit var binding: FragmentAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)
        binding.okButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.applicationVersion.text = BuildConfig.VERSION_NAME
    }
}