package com.example.lazycolumn.presentation.ui.MainScreen.PeoplePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazycolumn.model.NavigationPath
import com.example.lazycolumn.ui.theme.Dimensions
import com.example.lazycolumn.ui.theme.itemColor
import com.example.lazycolumn.viewmodel.PeopleViewModel


@Composable
fun VerticalList(navController: NavController,viewModel:PeopleViewModel){
    val people by viewModel.data

    val context = LocalContext.current

    LaunchedEffect(viewModel.filterKey.value) {
        viewModel.fetchDataFromJson(context)
    }

        LazyVerticalGrid(
            columns = GridCells.Fixed(Dimensions.numberOfColumn),
            verticalArrangement = Arrangement.spacedBy(Dimensions.spaceVerticalGrid),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.spaceVerticalGrid),
        ) {
            items(people){ person ->
                ItemList(
                    navController=navController,
                    viewModel = viewModel,
                    name = person.name,
                    id = person.id,
                    image = person.image)
            }
        }
    }

@Composable
fun ItemList(
    modifier: Modifier=Modifier,
    navController: NavController,
    viewModel: PeopleViewModel,
    id:Int=0,
    name: String,
    image:String
) {

    val context = LocalContext.current
    val nameCrop = if (name.length < 14) name else name.take(14) +"..."
    val imagerResId = context.resources.getIdentifier(image,"drawable",context.packageName)
    val imageId = if (imagerResId!=0) imagerResId else context.resources.getIdentifier("not_found","drawable",context.packageName)

    fun changeValue(){
        viewModel.fetchPersonFromJson(context,id)
        navController.navigate(NavigationPath.INFORMATION_PAGE)
    }

    Surface(
        shape = RoundedCornerShape(Dimensions.cornerRadius),
        modifier = Modifier
            .clickable { changeValue() }
            .padding(Dimensions.minPadding),
        color = itemColor
    ) {
        Column {
            Row(
                modifier =modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = modifier
                        .aspectRatio(1f)
                        .fillMaxWidth()
                        .padding(
                            horizontal = Dimensions.itemListPadding,
                            vertical = Dimensions.itemListPadding
)
                        .clip(RoundedCornerShape(Dimensions.cornerRadius)),
                    contentAlignment = Alignment.Center

                ){
                    Image(
                        painter = painterResource(id = imageId),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopStart,
                        contentDescription = "",
                        modifier = Modifier.defaultMinSize(Dimensions.minSizeBox)
                    )
                }
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimensions.itemListHeight),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = nameCrop,
                    color = Color.White,
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
        }
    }
}
