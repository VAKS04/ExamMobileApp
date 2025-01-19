package com.example.lazycolumn.presentation.ui.MainScreen.InformationScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.lazycolumn.ui.theme.Dimensions
import com.example.lazycolumn.ui.theme.backgroundColor
import com.example.lazycolumn.ui.theme.textColor
import com.example.lazycolumn.viewmodel.PeopleViewModel

@Composable
fun InformationScreen(viewModel: PeopleViewModel){
    val person by viewModel.person

    val nameHead = person?.name ?: "Null"
    val descriptionBody = person?.description ?: "Null"

    Scaffold(
        modifier = Modifier,
        containerColor = backgroundColor
    ) { rootPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(rootPadding),
        ) {
            Header(nameHead)
            Body(descriptionBody,person?.image)
        }
    }
}

@Composable
fun Header(name:String){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.headerPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = name,
                color = textColor,
                fontSize = Dimensions.headerTextSize
            )
        }

        HorizontalDivider(color = Color.Black, thickness = Dimensions.horizontalDivider)

    }

}

@Composable
fun Body(description:String,image:String?){
    val context = LocalContext.current

    val imagerResId = context.resources.getIdentifier(image,"drawable",context.packageName)
    val imageNotFound = context.resources.getIdentifier("not_found","drawable",context.packageName)

    val imageId = if (imagerResId!=0) imagerResId else imageNotFound
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .verticalScroll(scrollState)
        .padding(vertical = Dimensions.verticalPadding, horizontal = Dimensions.horizontalPadding)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (imagerResId !=0) {
                Box(
                    modifier = Modifier.padding(Dimensions.defaultPadding)
                        .height(Dimensions.maxSizeBox)
                        .width(Dimensions.maxSizeBox)
                        .clip(RoundedCornerShape(Dimensions.cornerRadius)),

                    contentAlignment = Alignment.Center
                )
                {
                    Image(
                        modifier = Modifier.defaultMinSize(Dimensions.maxDefaultMinSizeImage),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopStart,
                        painter = painterResource(id = imageId),
                        contentDescription = ""
                    )
                }
            }
        }

        Text(
            color = textColor,
            text = description,
            fontSize = Dimensions.descriptionTextSize,
            textAlign = TextAlign.Justify
        )
    }
}