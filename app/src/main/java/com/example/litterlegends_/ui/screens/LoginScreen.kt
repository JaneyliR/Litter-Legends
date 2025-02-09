package com.example.litterlegends_.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.litterlegends_.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var isLoginScreen by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var profileImage by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("New Hero") }
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
                    keyboardOptions = KeyboardOptions.Default // ✅ Fix keyboard input options
                )
            }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email) // ✅ Fix for email input
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password) // ✅ Fix for password input
            )

            if (!isLoginScreen) {
                OutlinedTextField(
                    value = profileImage,
                    onValueChange = { profileImage = it },
                    label = { Text("Profile Image URL (Optional)") },
                    keyboardOptions = KeyboardOptions.Default // ✅ Default for text input
                )

                OutlinedTextField(
                    value = status,
                    onValueChange = { status = it },
                    label = { Text("Status (Optional)") },
                    keyboardOptions = KeyboardOptions.Default // ✅ Default for text input
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (isLoginScreen) {
                    viewModel.loginUser(email, password,
                        onSuccess = { navController.navigate("home") },
                        onError = { errorMessage = it })
                } else {
                    viewModel.registerUser(email, username, password, profileImage, status,
                        onSuccess = { isLoginScreen = true },
                        onError = { errorMessage = it })
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
