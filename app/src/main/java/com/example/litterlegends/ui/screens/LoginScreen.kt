package com.example.litterlegends.ui.screens  // 🔥 Fix package name (remove `_`)

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.litterlegends.viewmodel.AuthViewModel
import com.example.litterlegends.viewmodel.ProfileViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    var isLoginScreen by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Litter Legends", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            if (!isLoginScreen) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    keyboardOptions = KeyboardOptions.Default
                )
            }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (isLoginScreen) {
                    authViewModel.loginUser(email, password,
                        onSuccess = {
                            navController.navigate("home") // ✅ Go to home
                        },
                        onError = { errorMessage = it }
                    )
                } else {
                    authViewModel.registerUser(email, username, password,
                        onSuccess = {
                            isLoginScreen = true // ✅ Switch to login after registration
                        },
                        onError = { errorMessage = it }
                    )
                }
            }) {
                Text(if (isLoginScreen) "Login" else "Register")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { isLoginScreen = !isLoginScreen }) {
                Text(if (isLoginScreen) "New here? Register" else "Already have an account? Login")
            }

            errorMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
