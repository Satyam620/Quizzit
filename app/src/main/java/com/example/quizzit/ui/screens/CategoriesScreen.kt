package com.example.quizzit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizzit.ui.Screen
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.Blue
import com.example.quizzit.ui.theme.Purple
import com.example.quizzit.ui.theme.Red

//TODO -Load data From Viewmodel
@Composable
fun CategoryScreen(viewModel: QuizViewModel, navController: NavHostController) {
    // Define gradient colors
    val colorStops = arrayOf(
        0.0f to Red,
        0.8f to Purple,
    )
    Scaffold(
        topBar = { TopBar("Category") },
        bottomBar = { BottomBar("Category", navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Brush.horizontalGradient(colorStops = colorStops))
                .fillMaxSize()
        ) {
            when {
                viewModel.categoriesState.value.loading -> {
                    CircularProgressIndicator(
                        strokeWidth = 10.dp,
                        modifier = Modifier.fillMaxSize().padding(50.dp,200.dp),
                        color = Color.White
                    )
                }

                viewModel.categoriesState.value.error != null -> {
                    Text(text = "ERROR OCCURRED")
                    println(viewModel.categoriesState.value.error)
                }

                else -> {
                    //Display Categories
                    CategoryList(viewModel, navController)
                }
            }
        }
    }
}

@Composable
fun CategoryList(viewModel: QuizViewModel, navController: NavHostController) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(viewModel.categoriesState.value.list) { category ->
            CategoryButton(category.name) {
                viewModel.fetchQuestions(category.id)
                navController.navigate(Screen.QuizScreen.route)
            }
        }
    }
}

@Composable
fun CategoryButton(category: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(Blue, Color.White)
    ) {
        Text(text = category, fontSize = 25.sp, lineHeight = 30.sp)
    }
}

@Preview
@Composable
fun CategoryPreview() {
    CategoryScreen(
        viewModel = QuizViewModel(),
        navController = NavHostController(LocalContext.current)
    )
}

