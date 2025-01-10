package com.example.lazycolumn.view.Template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.R

@Composable
fun TemplateChangePage(label:String, onClick: ()->Unit){

    var item by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = item ,
            onValueChange ={
                    newValue -> item = newValue
            },
            label = {
                Text(text = label)
            })

        Spacer(modifier = Modifier.height(20.dp))

        TemplateButton(
            onClick = { onClick() },
            text = stringResource(id = R.string.change_button))
    }
}