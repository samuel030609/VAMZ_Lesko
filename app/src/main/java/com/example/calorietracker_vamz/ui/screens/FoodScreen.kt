package com.example.calorietracker_vamz.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.calorietracker_vamz.R
import com.example.calorietracker_vamz.ViewModelInitializer
import com.example.calorietracker_vamz.data.Food

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    navigateToEatenFoodScreen: (Int) -> Unit,
    viewModel: FoodScreenViewModel = viewModel(factory = ViewModelInitializer.Factory)
) {
    var queryForSearch by remember { mutableStateOf("") }

    Column {
        Row (horizontalArrangement = Arrangement.Center) {
            TextField(
                value = queryForSearch,
                onValueChange = {newQuery -> queryForSearch = newQuery},
                label = { Text("Search") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        val foodUiState by viewModel.foodUiState.collectAsState()

        val foods = foodUiState.foods.filter { it.name.contains(queryForSearch, ignoreCase = true) }

        FoodBody(
            foods = foods,
            onFoodClick = { foodId -> navigateToEatenFoodScreen(foodId) }
        )
    }



}

@Composable
fun FoodBody(
    foods: List<Food>,
    onFoodClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if(foods.isEmpty()) {
            Text(
                text = "No food items found",
                fontSize = 20.sp
            )
        } else {
            FoodList(
                foods = foods,
                onFoodClick = onFoodClick,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }
    }

}

@Composable
fun FoodList(
    foods: List<Food>,
    onFoodClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier
    ) {

        items(items = foods, key = { food -> food.id }) { food ->
            FoodItem(
                modifier = Modifier.padding(8.dp),
                name = food.name,
                picture = food.picture,
                onClick = { onFoodClick(food.id) }
            )
        }
    }
}

@Composable
fun FoodItem(modifier: Modifier = Modifier, name: String = "", picture: String = "", onClick: () -> Unit) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row (modifier = Modifier
            .padding(8.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(picture)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.picture),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .padding(8.dp)
            )
            Text(
                text = name,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

