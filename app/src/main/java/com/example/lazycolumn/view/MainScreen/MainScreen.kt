package com.example.lazycolumn.view.MainScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.lazycolumn.R
import com.example.lazycolumn.model.NavItem
import com.example.lazycolumn.ui.theme.activeButton
import com.example.lazycolumn.ui.theme.backgroundColor
import com.example.lazycolumn.view.MainScreen.PeoplePage.PeoplePage
import com.example.lazycolumn.view.MainScreen.SettingsPage.SettingsPage
import com.example.lazycolumn.viewmodel.PeopleViewModel
import com.example.lazycolumn.viewmodel.UserViewModel

@Composable
fun MainScreen(
    navController: NavController,
    peopleViewModel: PeopleViewModel,
    userViewModel: UserViewModel){

    val navItemList = listOf(
        NavItem(
            stringResource(id = R.string.label_icon_home),
            Icons.Default.Home),
        NavItem(
            stringResource(id = R.string.label_icon_settings),
            Icons.Default.Settings)
    )

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,

            ) {
                navItemList.forEachIndexed{index, navItem ->
                    NavigationBarItem(
                        colors = NavigationBarItemColors(
                            selectedIconColor = Color.White,
                            disabledIconColor = Color.White,
                            disabledTextColor = Color.White,
                            selectedTextColor = Color.White,
                            selectedIndicatorColor = activeButton,
                            unselectedIconColor = Color.White,
                            unselectedTextColor = Color.White),
                        selected = selectedIndex==index ,
                        onClick = {
                            selectedIndex=index
                        },
                        icon = {
                            Icon(imageVector=navItem.image,contentDescription = "Icon")
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { padding->
        ChoosePage(
            modifier = Modifier.padding(padding),
            selectedIndex = selectedIndex,
            navController = navController,
            peopleViewModel = peopleViewModel,
            userViewModel = userViewModel
        )
    }
}

@Composable
fun ChoosePage(
    modifier: Modifier = Modifier,
    selectedIndex:Int,
    navController: NavController,
    peopleViewModel: PeopleViewModel,
    userViewModel: UserViewModel
){
    when(selectedIndex){
        0-> PeoplePage(modifier=modifier,navController =navController, viewModel = peopleViewModel)
        1-> SettingsPage(modifier=modifier,userViewModel)
    }
}