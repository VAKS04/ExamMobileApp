package com.example.lazycolumn.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lazycolumn.R
import com.example.lazycolumn.model.NavigationPath
import com.example.lazycolumn.ui.theme.textColor
import com.example.lazycolumn.viewmodel.PeopleViewModel


@Composable
fun RegistrationPage(navController: NavController,viewModel: PeopleViewModel){
    val context = LocalContext.current

    var loginValue by remember {
        mutableStateOf("")
    }

    var passwordValue1 by remember {
        mutableStateOf("")
    }

    var passwordValue2 by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = loginValue,
            onValueChange ={
                newValue -> loginValue = newValue
            },
            label = {
                Text(text = stringResource(id = R.string.login_field))
            })
        
        Spacer(modifier = Modifier.height(5.dp))
        
        TextField(
            value = passwordValue1,
            onValueChange ={
                newValue -> passwordValue1 = newValue
            },
            label = {
                Text(
                    text = stringResource(id = R.string.password1_field)
                )
            })

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            value = passwordValue2,
            onValueChange ={
                newValue -> passwordValue2 = newValue
            },
            label = {
                Text(
                    text = stringResource(id = R.string.password2_field)
                )
            })

        Row(modifier = Modifier.padding(15.dp)) {
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(NavigationPath.AUTH_PAGE){
                        popUpTo(NavigationPath.AUTH_PAGE){
                            inclusive = true
                        }
                    }
                },
                text = stringResource(id = R.string.registration_inscription),
                fontSize = 15.sp,
                color = textColor,
            )
        }

        LoginButton {
            viewModel.registration(
                context,
                loginValue,
                passwordValue1,
                passwordValue2
            )
        }
    }
}
