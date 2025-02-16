package com.example.lazycolumn.presentation.ui.MainScreen.PeoplePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazycolumn.model.Country
import com.example.lazycolumn.ui.theme.Dimensions
import com.example.lazycolumn.ui.theme.activeButton
import com.example.lazycolumn.ui.theme.buttonColor
import com.example.lazycolumn.ui.theme.textColor
import com.example.lazycolumn.viewmodel.PeopleViewModel


@Composable
fun FilterBar(modifier: Modifier = Modifier, viewModel: PeopleViewModel){

    val filterKey by viewModel.filterKey

    LazyRow(
        modifier = modifier.height(Dimensions.filterBarHeight),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(
            start = Dimensions.horizontalPadding,
            end = Dimensions.horizontalPadding)
    ) {
        items(Country.entries){item->
            Box(
                modifier = modifier
                    .size(
                        width = (item.displayName.length + 100).dp,
                        height = Dimensions.itemBarHeight)
                    .clip(
                        RoundedCornerShape(Dimensions.cornerRadius)
                    )
                    .background(
                        if (filterKey == item) activeButton else buttonColor)
                    .clickable {
                        viewModel.updateFilterKey(item)
                    },
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = item.displayName,
                    color = textColor,
                    fontSize = Dimensions.defaultTextSize)
            }
        }
    }
}