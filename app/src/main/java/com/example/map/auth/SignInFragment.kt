package com.example.map.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.map.R
import com.example.map.Repositories
import com.example.map.databinding.FragmentSignInBinding
import com.example.map.utils.viewModelCreator

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModelCreator {
        SignInViewModel(Repositories.accountsRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)

        observeState()

        binding.createAccountButton.setOnClickListener { onCreateAccountClick() }
        binding.signInButton.setOnClickListener { onSignInClick() }
    }

    private fun observeState() = viewModel.state.observe(viewLifecycleOwner) {
        binding.progressBar.visibility = View.VISIBLE
        if (it.successAuth)
            goToTabsFragment()
        else{
            if (it.authError) processAuthError()
            if (it.emailError || it.passwordError)
                processEmptyField(it.emailError, it.passwordError)
            if (it.passwordMismatch) processPasswordError()
        }
        binding.progressBar.visibility = View.GONE
    }

    private fun processPasswordError() {
        binding.passwordEditText.error = getString(R.string.password_mismatch)
    }

    private fun processEmptyField(emailError: Boolean, passwordError: Boolean){
        binding.emailEditText.error = if (emailError) getString(R.string.field_is_empty) else null
        binding.passwordEditText.error = if (passwordError) getString(R.string.field_is_empty) else null
    }

    private fun processAuthError(){
        binding.emailEditText.error = getString(R.string.account_is_not_created)
    }

    private fun goToTabsFragment(){
        val directions = SignInFragmentDirections.actionSignInFragmentToTabsFragment()
        findNavController().navigate(directions)
    }

    private fun onCreateAccountClick(){
        val directions = SignInFragmentDirections.actionSignInFragmentToCreateAccountFragment()
        findNavController().navigate(directions)
    }

    private fun onSignInClick(){
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        viewModel.signIn(email, password)
    }

}
