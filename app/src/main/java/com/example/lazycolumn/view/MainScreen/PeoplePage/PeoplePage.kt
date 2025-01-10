package com.example.lazycolumn.view.MainScreen.PeoplePage

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lazycolumn.viewmodel.PeopleViewModel

@Composable
fun PeoplePage(modifier: Modifier, navController: NavController, viewModel: PeopleViewModel){
    Column(modifier = modifier) {
        FilterBar(viewModel = viewModel)
        VerticalList(navController = navController, viewModel = viewModel)
    }
}