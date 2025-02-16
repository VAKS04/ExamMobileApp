package com.example.lazycolumn.presentation.ui.MainScreen.SettingsPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazycolumn.R
import com.example.lazycolumn.ui.theme.itemColor
import com.example.lazycolumn.ui.theme.textColor
import com.example.lazycolumn.ui.theme.welcomeBarColor
import com.example.lazycolumn.viewmodel.UserViewModel

@Composable
fun SettingsPage(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel
){

    val user by viewModel.user

    val itemMenu = listOf(
        "Сменить логин",
        "Сменить пароль",
    )

    Column(
        modifier = modifier.padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .background(welcomeBarColor),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = stringResource(id = R.string.welcome_to_user) + user!!.login,
                color = textColor,
                fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        itemMenu.forEachIndexed(){indexed , iM->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(itemColor),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = iM,
                    color = textColor,
                    fontSize = 20.sp
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//fun SettingsPage(modifier: Modifier = Modifier){
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Settings page",
//            color = textColor
//        )
//    }
//}