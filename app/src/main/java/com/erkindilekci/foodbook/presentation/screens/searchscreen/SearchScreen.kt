package com.erkindilekci.foodbook.presentation.screens.searchscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erkindilekci.foodbook.util.navigation.Screen

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val searchQuery by viewModel.searchQuery
    val searchedMeals by viewModel.searchedMeals.collectAsState()

    Scaffold(
        topBar = {
            SearchScreenTopAppBar(
                text = searchQuery,
                onTextChange = { viewModel.updateSearchQuery(it) },
                onSearchClicked = { viewModel.getSearchedMeal(it) },
                onCloseClicked = { navController.popBackStack() }
            )
        },
        content = { paddingValues ->
            if (searchedMeals.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = 8.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    items(searchedMeals) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    navController.navigate(Screen.DetailsScreen.passId(it.idMeal))
                                }
                        ) {
                            AsyncImage(
                                model = it.strMealThumb,
                                contentDescription = it.strMeal,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                            it.strMeal?.let {
                                Text(
                                    text = it,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(
                                        start = 5.dp,
                                        end = 5.dp,
                                        bottom = 10.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
