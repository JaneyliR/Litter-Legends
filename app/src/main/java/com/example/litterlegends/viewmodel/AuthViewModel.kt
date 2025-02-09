package com.example.litterlegends.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    fun login(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            // 🔹 Simulated login logic (Replace with Firebase Auth)
            if (email == "test@example.com" && password == "password") {
                onResult(true) // ✅ Login successful
            } else {
                onResult(false) // ❌ Login failed
            }
        }
    }

    fun register(
        email: String,
        username: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            // 🔹 Simulated registration logic (Replace with Firebase or DB)
            if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                onResult(true) // ✅ Registration successful
            } else {
                onResult(false) // ❌ Registration failed
            }
        }
    }
}
