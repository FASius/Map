package com.example.map.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.map.model.AccountAlreadyCreatedException
import com.example.map.model.EmptyFieldException
import com.example.map.model.Field
import com.example.map.model.account.AccountRepository
import com.example.map.model.account.SignUpData

class CreateAccountViewModel(
    private val accountRepository: AccountRepository
): ViewModel() {

    private val _state = MutableLiveData(SignUpState())
    val state: LiveData<SignUpState> = _state

    fun signUp(signUpData: SignUpData){
        try {
            accountRepository.signUp(signUpData)
            signIn(signUpData.email, signUpData.password)
            navigateToTabs()
        } catch (e: EmptyFieldException){
            showEmptyFieldError(e)
        } catch (e: AccountAlreadyCreatedException){
            showAccountError()
        }
    }

    private fun signIn(email:String, password:String){
        accountRepository.signIn(email, password)
    }

    private fun navigateToTabs(){
        _state.value = SignUpState(
            successAuth = true
        )
    }

    private fun showEmptyFieldError(e: EmptyFieldException){
        _state.value = SignUpState(
            usernameError = e.field == Field.Username,
            emailError = e.field == Field.Email,
            passwordError = e.field == Field.Password
        )
    }

    private fun showAccountError(){
        _state.value = SignUpState(
            authError = true
        )
    }

    data class SignUpState(
        val successAuth:Boolean = false,
        val usernameError:Boolean = false,
        val emailError:Boolean = false,
        val passwordError:Boolean = false,
        val authError:Boolean = false
    )
}