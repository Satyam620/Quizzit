package com.example.quizzit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.QuizzitTheme

@Composable
fun HomeScreen(viewModel: QuizViewModel, navHostController: NavHostController) {
    if (!viewModel.login.value) {
        RegisterScreen(viewModel)
        LoginScreen(viewModel)
    }
    QuizzitTheme {
        Scaffold(
            topBar = {
                TopBar("Home")
            },
            bottomBar = {
                BottomBar("Home", navHostController)
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "WELCOME TO THE QUIZZIT APP",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 50.sp
                )

            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(
        viewModel = QuizViewModel(),
        navHostController = NavHostController(LocalContext.current)
    )
}


