package com.example.lazycolumn.view.Template

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazycolumn.ui.theme.buttonColor
import com.example.lazycolumn.ui.theme.textColor

@Composable
fun TemplateButton(onClick: ()-> Unit, text:String){

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
            text = text,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
//            modifier = Modifier.width(120.dp)
        )
    }
}