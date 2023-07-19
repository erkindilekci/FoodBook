package com.erkindilekci.foodbook.presentation.screens.detailsscreen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.erkindilekci.foodbook.R

@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val meal by viewModel.mealDetails.collectAsState()

    val context = LocalContext.current

    meal?.let { meal ->
        Scaffold(
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f)
                    ) {
                        AsyncImage(
                            model = meal.strMealThumb,
                            contentDescription = meal.strMeal,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        meal.strMeal?.let {
                            Text(
                                text = it,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(24.dp)
                                    .align(Alignment.BottomStart)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Category,
                                contentDescription = stringResource(id = R.string.category_icon),
                                modifier = Modifier.size(26.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            meal.strCategory?.let {
                                Text(
                                    text = it,
                                    fontSize = 20.sp
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = stringResource(R.string.location_icon),
                                modifier = Modifier.size(26.dp)
                            )
                            meal.strArea?.let {
                                Text(
                                    text = it,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (!meal.strIngredient1.isNullOrEmpty()) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Ingredients: ",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, top = 4.dp)
                            ) {
                                Text(
                                    text = "1. ${meal.strIngredient1} (${meal.strMeasure1})",
                                    fontSize = 16.sp
                                )
                                if (!meal.strIngredient2.isNullOrEmpty()) {
                                    Text(
                                        text = "2. ${meal.strIngredient2} (${meal.strMeasure2})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient3.isNullOrEmpty()) {
                                    Text(
                                        text = "3. ${meal.strIngredient3} (${meal.strMeasure3})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient4.isNullOrEmpty()) {
                                    Text(
                                        text = "4. ${meal.strIngredient4} (${meal.strMeasure4})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient5.isNullOrEmpty()) {
                                    Text(
                                        text = "5. ${meal.strIngredient5} (${meal.strMeasure5})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient6.isNullOrEmpty()) {
                                    Text(
                                        text = "6. ${meal.strIngredient6} (${meal.strMeasure6})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient7.isNullOrEmpty()) {
                                    Text(
                                        text = "7. ${meal.strIngredient7} (${meal.strMeasure7})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient8.isNullOrEmpty()) {
                                    Text(
                                        text = "8. ${meal.strIngredient8} (${meal.strMeasure8})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient9.isNullOrEmpty()) {
                                    Text(
                                        text = "9. ${meal.strIngredient9} (${meal.strMeasure9})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient10.isNullOrEmpty()) {
                                    Text(
                                        text = "10. ${meal.strIngredient10} (${meal.strMeasure10})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient11.isNullOrEmpty()) {
                                    Text(
                                        text = "11. ${meal.strIngredient11} (${meal.strMeasure11})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient12.isNullOrEmpty()) {
                                    Text(
                                        text = "12. ${meal.strIngredient12} (${meal.strMeasure12})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient13.isNullOrEmpty()) {
                                    Text(
                                        text = "13. ${meal.strIngredient13} (${meal.strMeasure13})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient14.isNullOrEmpty()) {
                                    Text(
                                        text = "14. ${meal.strIngredient14} (${meal.strMeasure14})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient15.isNullOrEmpty()) {
                                    Text(
                                        text = "15. ${meal.strIngredient15} (${meal.strMeasure15})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient16.isNullOrEmpty()) {
                                    Text(
                                        text = "16. ${meal.strIngredient16} (${meal.strMeasure16})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient17.isNullOrEmpty()) {
                                    Text(
                                        text = "17. ${meal.strIngredient17} (${meal.strMeasure17})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient18.isNullOrEmpty()) {
                                    Text(
                                        text = "18. ${meal.strIngredient18} (${meal.strMeasure18})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient19.isNullOrEmpty()) {
                                    Text(
                                        text = "19. ${meal.strIngredient19} (${meal.strMeasure19})",
                                        fontSize = 16.sp
                                    )
                                }

                                if (!meal.strIngredient20.isNullOrEmpty()) {
                                    Text(
                                        text = "20. ${meal.strIngredient20} (${meal.strMeasure20})",
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (!meal.strInstructions.isNullOrEmpty()) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Instructions: ",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Text(
                                text = meal.strInstructions,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                textAlign = TextAlign.Justify
                            )
                        }
                    }

                    if (!meal.strYoutube.isNullOrEmpty()) {
                        Spacer(modifier = (Modifier.height(16.dp)))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Youtube: ",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            Image(
                                painter = painterResource(id = R.drawable.ic_youtube_logo),
                                contentDescription = stringResource(id = R.string.youtube_icon),
                                modifier = Modifier
                                    .height(30.dp)
                                    .clickable {
                                        val intent =
                                            Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube))
                                        ContextCompat.startActivity(context, intent, null)
                                    }
                            )
                        }
                        Spacer(modifier = (Modifier.height(16.dp)))

                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.upsertUpdate(meal)
                        val string = "You have successfully saved '${meal.strMeal}' to favorites"
                        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
                    },
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.favorite_icon)
                    )
                }
            }
        )
    }

    BackHandler {
        navController.popBackStack()
    }
}
