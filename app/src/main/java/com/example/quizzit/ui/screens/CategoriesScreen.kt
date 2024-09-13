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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizzit.ui.Screen
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.QuizzitTheme

//TODO -Load data From Viewmodel
@Composable
fun CategoryScreen(viewModel: QuizViewModel, navController: NavHostController) {
    QuizzitTheme {
        Scaffold(
            topBar = { TopBar("Category") },
            bottomBar = { BottomBar("Category", navController) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    viewModel.categoriesState.value.loading -> {
                        CircularProgressIndicator(
                            strokeWidth = 10.dp,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(50.dp, 200.dp),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    viewModel.categoriesState.value.error != null -> {
                        Text(text = "ERROR OCCURRED", fontSize = 25.sp, color = Color.Red)
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
}

@Composable
fun CategoryList(viewModel: QuizViewModel, navController: NavHostController) {
    QuizzitTheme {
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
}

@Composable
fun CategoryButton(category: String, onClick: () -> Unit) {
    QuizzitTheme {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.secondaryContainer,
                MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = category, fontSize = 25.sp, lineHeight = 30.sp)
        }
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

