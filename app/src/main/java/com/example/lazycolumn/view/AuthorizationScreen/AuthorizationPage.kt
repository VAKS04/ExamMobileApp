package com.example.lazycolumn.view.AuthorizationScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lazycolumn.R
import com.example.lazycolumn.ui.theme.Dimensions
import com.example.lazycolumn.model.NavigationPath
import com.example.lazycolumn.ui.theme.textColor
import com.example.lazycolumn.view.Template.TemplateButton
import com.example.lazycolumn.viewmodel.UserViewModel

@Composable
fun AuthorizationPage(
    navController: NavController,
    viewModel: UserViewModel
){
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

        Spacer(modifier = Modifier.height(Dimensions.minHeightSpace))

        TextField(
            modifier = Modifier.width(Dimensions.textFieldWidth),
            label = {
                Text(
                    stringResource(id = R.string.password1_field)
                )},
            maxLines = 1,
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            value = passwordValue,
            onValueChange ={
                newValue -> passwordValue = newValue
            }
        )

        Row(modifier = Modifier.padding(Dimensions.descriptionPadding)) {
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
                fontSize = Dimensions.defaultTextSize
            )
        }
        
        Spacer(modifier = Modifier.height(Dimensions.maxHeightSpace))

        TemplateButton(onClick = {
            viewModel.authorization(
                context,
                loginValue,
                passwordValue)
        },text = stringResource(id = R.string.login_button))
    }
}