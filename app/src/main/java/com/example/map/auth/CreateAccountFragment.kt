package com.example.map.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.map.R
import com.example.map.Repositories
import com.example.map.databinding.FragmentCreateAccountBinding
import com.example.map.model.account.SignUpData
import com.example.map.utils.viewModelCreator

class CreateAccountFragment : Fragment(R.layout.fragment_create_account) {

    private lateinit var binding: FragmentCreateAccountBinding
    private val viewModel: CreateAccountViewModel by viewModelCreator {
        CreateAccountViewModel(Repositories.accountsRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateAccountBinding.bind(view)

        observeState()
        binding.createAccountButton.setOnClickListener {
            onCreateAccountClick()
        }
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner){
        binding.progressBar.visibility = View.VISIBLE
        if (it.successAuth)
            goToTabsFragment()
        else{
            if (it.authError) processAuthError()
            if (it.usernameError || it.emailError || it.passwordError)
                processEmptyField(it.usernameError ,it.emailError, it.passwordError)
        }
        binding.progressBar.visibility = View.GONE
    }

    private fun processEmptyField(usernameError:Boolean, emailError: Boolean, passwordError: Boolean) {
        if (usernameError) binding.usernameEditText.error = getString(R.string.field_is_empty)
        if (emailError) binding.emailEditText.error = getString(R.string.field_is_empty)
        if (passwordError) binding.passwordEditText.error = getString(R.string.field_is_empty)
    }

    private fun processAuthError() {
        binding.emailEditText.error = getString(R.string.account_already_created)
    }

    private fun goToTabsFragment(){
        val directions = CreateAccountFragmentDirections.actionCreateAccountFragmentToTabsFragment()
        findNavController().navigate(directions)
    }

    private fun onCreateAccountClick(){
        val signUpData = SignUpData(
            binding.usernameEditText.text.toString(),
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString()
        )
        viewModel.signUp(signUpData)
    }

}