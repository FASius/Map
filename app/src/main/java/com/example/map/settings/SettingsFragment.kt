package com.example.map.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.map.R
import com.example.map.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        binding.aboutButton.setOnClickListener {
            val directions = SettingsFragmentDirections.actionSettingsFragmentToAboutFragment()
            findNavController().navigate(directions)
        }

        binding.changePasswordButton.setOnClickListener {
            val directions = SettingsFragmentDirections.actionSettingsFragmentToChangePasswordFragment()
            findNavController().navigate(directions)
        }

        binding.editProfileButton.setOnClickListener {
            val directions = SettingsFragmentDirections.actionSettingsFragmentToEditProfileFragment()
            findNavController().navigate(directions)
        }

        binding.logoutButton.setOnClickListener {
            //TODO
        }
    }
}