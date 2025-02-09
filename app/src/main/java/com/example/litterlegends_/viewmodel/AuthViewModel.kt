package com.example.litterlegends_.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.litterlegends_.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun registerUser(email: String, username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = authRepository.registerUser(email, username, password)
            onResult(success)
        }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = authRepository.loginUser(email, password)
            onResult(success)
        }
    }
}
