package com.example.calorietracker_vamz.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyBottomBar(navController: NavController) {
    val listOfItem = listOf(
        Screens.StatisticScreen,
        Screens.FoodScreen,
        Screens.EatenFoodsScreen
    )

    NavigationBar (
        modifier = Modifier.fillMaxWidth()
    )
    {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        listOfItem.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon!!,
                        contentDescription = null,
                        tint = if (currentRoute == item.path) Color.DarkGray else Color.Gray,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.resourceId!!),
                        style = if (currentRoute == item.path) TextStyle(fontSize = 10.sp,color = Color.DarkGray) else TextStyle(fontSize = 10.sp,color = Color.Gray)
                    )
                },
                selected = currentRoute == item.path,
                onClick = {
                    navController.navigate(item.path) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}