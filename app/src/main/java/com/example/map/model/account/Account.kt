package com.example.map.model.account

import com.example.map.model.EmptyFieldException
import com.example.map.model.Field

data class Account(
    val id: Long,
    var username:String,
    val email:String,
    var password:String
) {
    companion object{
        const val NO_ACCOUNT = -1L
    }
}

class SignUpData(
    val username:String,
    val email:String,
    val password:String
){
    fun validate() {
        when {
            username.isBlank() -> throw EmptyFieldException(Field.Username)
            email.isBlank() -> throw EmptyFieldException(Field.Email)
            password.isBlank() -> throw EmptyFieldException(Field.Password)
        }
    }
}