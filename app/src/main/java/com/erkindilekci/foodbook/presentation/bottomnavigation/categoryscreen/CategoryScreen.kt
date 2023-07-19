package com.erkindilekci.foodbook.presentation.bottomnavigation.categoryscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erkindilekci.foodbook.presentation.bottomnavigation.util.BottomNavigationBar
import com.erkindilekci.foodbook.util.navigation.Screen

@Composable
fun CategoryScreen(
    viewModel: CategoryScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val categories by viewModel.categories.collectAsState()

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    categories?.let { categories ->
                        items(categories) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        Screen.CategoryMealsScreen.passCategory(it.strCategory)
                                    )
                                }) {
                                AsyncImage(
                                    model = it.strCategoryThumb,
                                    contentDescription = it.strCategory,
                                    modifier = Modifier
                                        .size(150.dp)
                                )
                                Text(
                                    text = it.strCategory,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 21.sp
                                )
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}
