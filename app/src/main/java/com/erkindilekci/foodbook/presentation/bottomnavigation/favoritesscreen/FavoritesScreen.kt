package com.erkindilekci.foodbook.presentation.bottomnavigation.favoritesscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erkindilekci.foodbook.R
import com.erkindilekci.foodbook.data.model.Meal
import com.erkindilekci.foodbook.presentation.bottomnavigation.util.BottomNavigationBar
import com.erkindilekci.foodbook.util.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val favorites by viewModel.favoriteMeals.collectAsState()
    val scope = rememberCoroutineScope()

    var isEmpty by remember { mutableStateOf(false) }

    scope.launch {
        delay(200)
        if (favorites.isEmpty()) {
            isEmpty = true
        }
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isEmpty) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.you_haven_t_add_any_favorite_meal_yet),
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(32.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        items(favorites) { meal ->
                            val dismissState = rememberDismissState()
                            val dismissDirection = dismissState.dismissDirection
                            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
                            if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                                viewModel.deleteMeal(meal)
                            }

                            val degrees by animateFloatAsState(
                                targetValue = if (dismissState.targetValue == DismissValue.Default) 0f else -45f
                            )

                            SwipeToDismiss(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp)),
                                state = dismissState,
                                directions = setOf(DismissDirection.EndToStart),
                                background = { RedBackground(degrees = degrees) },
                                dismissContent = {
                                    MealItem(
                                        meal = meal, onItemClick = {
                                            navController.navigate(
                                                Screen.DetailsScreen.passId(it)
                                            )
                                        }
                                    )
                                }
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
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

@Composable
fun RedBackground(degrees: Float) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxSize()
            .background(Color.Red)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier.rotate(degrees),
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete),
            tint = Color.White
        )
    }
}

@Composable
fun MealItem(meal: Meal, onItemClick: (String) -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onItemClick(meal.idMeal) },
        shape = RoundedCornerShape(15.dp),
    ) {
        Row {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = meal.strMeal,
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
                meal.strMeal?.let {
                    Text(
                        text = it,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = stringResource(R.string.category_icon)
                    )
                    meal.strCategory?.let {
                        Text(
                            text = it,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                meal.strYoutube?.let {
                    Image(
                        painter = painterResource(id = R.drawable.ic_youtube_logo),
                        contentDescription = stringResource(R.string.youtube_icon),
                        modifier = Modifier
                            .height(30.dp)
                            .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                startActivity(context, intent, null)
                            }
                    )
                }
            }
        }
    }
}
