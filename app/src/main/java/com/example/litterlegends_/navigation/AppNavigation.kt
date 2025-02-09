package com.example.litterlegends_.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.litterlegends_.ui.screens.HomeScreen
import com.example.litterlegends_.ui.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()  // ✅ Fix NavController type

    NavHost(navController = navController, startDestination = "login") {
        composable(route = "login") { LoginScreen(navController) }  // ✅ Fix: Ensure function matches signature
        composable(route = "home") { HomeScreen(navController) }    // ✅ Fix: Ensure function matches signature
    }
}
