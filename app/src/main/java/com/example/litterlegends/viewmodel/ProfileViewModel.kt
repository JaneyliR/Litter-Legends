package com.example.litterlegends.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    val email: String,
    val imageUrl: String,
    val status: String
)

class ProfileViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    fun getProfile(email: String) {
        viewModelScope.launch {
            // 🔹 Simulate fetching user profile from a database (Replace with Firebase or local DB)
            if (email.isNotEmpty()) {
                _userProfile.value = UserProfile(
                    email = email,
                    imageUrl = "https://example.com/default-profile.jpg", // 🔹 Replace with actual image
                    status = "Litter Hero" // 🔹 Default status
                )
            }
        }
    }
}
