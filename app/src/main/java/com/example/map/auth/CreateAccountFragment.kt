package com.example.map.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.map.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateAccountBinding.bind(view)

        binding.createAccountButton.setOnClickListener {
            // TODO
        }
    }
}