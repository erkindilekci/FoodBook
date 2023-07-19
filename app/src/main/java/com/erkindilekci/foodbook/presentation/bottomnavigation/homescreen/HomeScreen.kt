package com.erkindilekci.foodbook.presentation.bottomnavigation.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erkindilekci.foodbook.R
import com.erkindilekci.foodbook.presentation.bottomnavigation.util.BottomNavigationBar
import com.erkindilekci.foodbook.util.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val randomMeal by viewModel.randomMeal.collectAsState()
    val mostPopularMeals by viewModel.mostPopularMeals.collectAsState()
    val categories by viewModel.categories.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.W700,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                actions = {
                    IconButton(modifier = Modifier
                        .padding(end = 8.dp)
                        .size(40.dp),
                        onClick = { navController.navigate(Screen.SearchScreen.route) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_icon),
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Today's special",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clickable {
                            randomMeal?.let {
                                navController.navigate(Screen.DetailsScreen.passId(it.idMeal))
                            }
                        }
                ) {
                    AsyncImage(
                        model = randomMeal?.strMealThumb,
                        contentDescription = randomMeal?.strMeal,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(15.dp))
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Popular dishes",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp)
                )

                LazyRow {
                    items(mostPopularMeals) {
                        AsyncImage(
                            model = it.strMealThumb,
                            contentDescription = it.strMeal,
                            modifier = Modifier
                                .size(180.dp, 100.dp)
                                .padding(end = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    navController.navigate(Screen.DetailsScreen.passId(it.idMeal))
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Category",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(100.dp)
                ) {
                    categories?.let { categories ->
                        items(categories) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                AsyncImage(
                                    model = it.strCategoryThumb,
                                    contentDescription = it.strCategory,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clickable {
                                            navController.navigate(
                                                Screen.CategoryMealsScreen.passCategory(it.strCategory)
                                            )
                                        }
                                )
                                Text(
                                    text = it.strCategory,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
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
