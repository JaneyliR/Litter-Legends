package com.example.litterlegends.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            // 🔹 Simulated login logic (Replace with Firebase Auth)
            if (email == "test@example.com" && password == "password") {
                onSuccess() // ✅ Login successful
            } else {
                onError("Invalid email or password") // ❌ Login failed
            }
        }
    }

    fun registerUser(
        email: String,
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            // 🔹 Simulated registration logic (Replace with Firebase or database)
            if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                onSuccess() // ✅ Registration successful
            } else {
                onError("Please fill in all fields") // ❌ Registration failed
            }
        }
    }
}
