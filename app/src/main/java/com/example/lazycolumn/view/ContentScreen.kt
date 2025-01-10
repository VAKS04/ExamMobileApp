package com.example.lazycolumn.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lazycolumn.ui.theme.LazyColumnTheme
import com.example.lazycolumn.ui.theme.backgroundColor
import com.example.lazycolumn.viewmodel.PeopleViewModel

@Composable
fun ContentScreen(viewModel: PeopleViewModel = PeopleViewModel()) {
    val navController = rememberNavController()

    val isLogin by viewModel.isLogin

    Surface(
        color = backgroundColor
    ) {

//    }
//    LazyColumnTheme {
        NavHost(
            navController = navController,
            startDestination = if (isLogin) "list_screen" else "authorization_page"
        )
        {
            composable("authorization_page"){
                AuthorizationPage(navController = navController,viewModel)
            }

            composable("registration_page"){
                RegistrationPage(navController = navController,viewModel)
            }

            composable("list_screen") {
                MainScreen(navController = navController, viewModel = viewModel)
            }

            composable("information") {
                InformationScreen(viewModel=viewModel)
            }
        }
    }
}
//}