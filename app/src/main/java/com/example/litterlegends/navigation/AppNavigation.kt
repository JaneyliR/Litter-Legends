package com.example.litterlegends.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    SetupNavGraph(navController)  // ✅ Now calls `NavGraph.kt`
}
