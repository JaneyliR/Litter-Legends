package com.example.litterlegends_.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.litterlegends_.viewmodel.ProfileViewModel
import com.example.litterlegends_.R

@Composable
fun ProfileSetupScreen(navController: NavController, profileViewModel: ProfileViewModel) {
    var profileImage by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Setup Profile", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Profile Image Preview
        if (profileImage.isNotEmpty()) {
            Image(
                painter = rememberImagePainter(profileImage),
                contentDescription = "Profile Image",
                modifier = Modifier.size(100.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.default_profile),
                contentDescription = "Default Profile Image",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ✅ Profile Image URL Input
        OutlinedTextField(
            value = profileImage,
            onValueChange = { profileImage = it },
            label = { Text("Profile Image URL") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ✅ Status Input
        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ✅ Show Error Message if Fields are Empty
        if (errorMessage != null) {
            Text(errorMessage!!, color = Color.Red, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ Save Button
        Button(
            onClick = {
                if (profileImage.isEmpty() || status.isEmpty()) {
                    errorMessage = "Profile image and status cannot be empty."
                } else {
                    profileViewModel.saveProfile(profileImage, status)
                    navController.navigate("home") // ✅ Navigate to Home after setup
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Profile")
        }
    }
}
