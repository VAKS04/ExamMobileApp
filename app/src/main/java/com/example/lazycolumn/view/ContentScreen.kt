package com.example.lazycolumn.view

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lazycolumn.model.NavigationPath
import com.example.lazycolumn.ui.theme.backgroundColor
import com.example.lazycolumn.view.AuthorizationScreen.AuthorizationPage
import com.example.lazycolumn.view.AuthorizationScreen.RegistrationPage
import com.example.lazycolumn.view.MainScreen.InformationScreen.InformationScreen
import com.example.lazycolumn.view.MainScreen.MainScreen
import com.example.lazycolumn.viewmodel.PeopleViewModel
import com.example.lazycolumn.viewmodel.UserViewModel

@Composable
fun ContentScreen(
    peopleViewModel: PeopleViewModel = PeopleViewModel(),
    userViewModel: UserViewModel = UserViewModel())
{
    val navController = rememberNavController()

    val isLogin by userViewModel.isLogin

    Surface(
        color = backgroundColor
    ) {

        NavHost(
            navController = navController,
            startDestination = if (isLogin) NavigationPath.PEOPLE_PAGE else NavigationPath.AUTH_PAGE
        )
        {
            composable(NavigationPath.AUTH_PAGE){
                AuthorizationPage(navController = navController,userViewModel)
            }

            composable(NavigationPath.REG_PAGE){
                RegistrationPage(navController = navController,userViewModel)
            }

            composable(NavigationPath.PEOPLE_PAGE) {
                MainScreen(
                    navController = navController,
                    peopleViewModel = peopleViewModel,
                    userViewModel = userViewModel)
            }

            composable(NavigationPath.INFORMATION_PAGE) {
                InformationScreen(viewModel=peopleViewModel)
            }
        }
    }
}
//}