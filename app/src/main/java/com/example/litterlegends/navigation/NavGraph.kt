package com.example.litterlegends.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.litterlegends.ui.screens.HomeScreen
import com.example.litterlegends.ui.screens.LoginScreen
import com.example.litterlegends.ui.screens.ProfileSetupScreen
import com.example.litterlegends.ui.screens.RegisterScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        addAuthGraph(navController)  // ✅ Separate Authentication Routes
        addMainGraph(navController)  // ✅ Separate Main App Routes
    }
}

fun NavGraphBuilder.addAuthGraph(navController: NavHostController) {
    composable("login") { LoginScreen(navController) }
    composable("register") { RegisterScreen(navController) }
    composable("profileSetup") { ProfileSetupScreen(navController) }
}

fun NavGraphBuilder.addMainGraph(navController: NavHostController) {
    composable("home") { HomeScreen(navController) }
}
