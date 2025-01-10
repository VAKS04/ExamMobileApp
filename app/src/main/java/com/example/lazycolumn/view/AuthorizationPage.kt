package com.example.lazycolumn.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lazycolumn.R
import com.example.lazycolumn.model.Dimensions
import com.example.lazycolumn.model.NavigationPath
import com.example.lazycolumn.ui.theme.buttonColor
import com.example.lazycolumn.ui.theme.textColor
import com.example.lazycolumn.viewmodel.PeopleViewModel
import kotlin.math.sin

@Composable
fun AuthorizationPage(navController: NavController,viewModel: PeopleViewModel){
    val context = LocalContext.current

    var loginValue by remember {
        mutableStateOf("")
    }
    var passwordValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.width(Dimensions.textFieldWidth),
            label = {
                Text(
                    stringResource(id = R.string.login_field)
                )},
            maxLines = 1,
            singleLine = true,
            value = loginValue,
            onValueChange ={
                newValue -> loginValue = newValue
            }
        )

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            modifier = Modifier.width(Dimensions.textFieldWidth),
            label = {
                Text(
                    stringResource(id = R.string.password1_field)
                )},
            maxLines = 1,
            singleLine = true,
            value = passwordValue,
            onValueChange ={
                newValue -> passwordValue = newValue
            }
        )

        Row(modifier = Modifier.padding(15.dp)) {
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(NavigationPath.REG_PAGE){
                        popUpTo(NavigationPath.REG_PAGE){
                            inclusive = true
                        }

                    }
                },
                text = stringResource(id = R.string.authorization_inscription),
                color = textColor,
                fontSize = 15.sp
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        LoginButton {
            viewModel.authorization(
                context,
                loginValue,
                passwordValue)
        }
    }
}