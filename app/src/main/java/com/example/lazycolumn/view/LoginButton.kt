package com.example.lazycolumn.view

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazycolumn.ui.theme.buttonColor
import com.example.lazycolumn.ui.theme.textColor
import com.example.lazycolumn.R

@Composable
fun LoginButton(onClick: ()-> Unit){

    Button(
        onClick = {
            onClick()
        },
        colors = ButtonColors(
            containerColor = buttonColor,
            contentColor = textColor,
            disabledContentColor = textColor,
            disabledContainerColor = buttonColor
        )
    ) {
        Text(
            text = stringResource(id = R.string.login_button),
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(100.dp)
        )
    }
}