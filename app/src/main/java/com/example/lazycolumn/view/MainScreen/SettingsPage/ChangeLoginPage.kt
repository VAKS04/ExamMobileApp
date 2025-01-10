package com.example.lazycolumn.view.MainScreen.SettingsPage


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lazycolumn.R
import com.example.lazycolumn.view.Template.TemplateChangePage

@Preview(showBackground = true)
@Composable
fun ChangeLoginPage(){
    TemplateChangePage(label = stringResource(id = R.string.login_field)){}
}