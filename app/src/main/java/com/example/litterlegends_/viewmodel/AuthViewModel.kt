package com.example.litterlegends_.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.litterlegends_.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    fun registerUser(
        email: String,
        username: String,
        password: String,
        profileImage: String,
        status: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val isRegistered = authRepository.registerUser(email, username, password, profileImage, status)
            if (isRegistered) {
                onSuccess()
            } else {
                onError("User already exists!")
            }
        }
    }

    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val isAuthenticated = authRepository.loginUser(email, password)
            if (isAuthenticated) {
                onSuccess()
            } else {
                onError("Invalid email or password!")
            }
        }
    }
}
