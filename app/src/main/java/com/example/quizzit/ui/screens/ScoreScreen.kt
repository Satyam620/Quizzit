package com.example.quizzit.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizzit.R
import com.example.quizzit.ui.Screen
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.QuizzitTheme

@Composable
fun ScoreScreen(viewModel: QuizViewModel, navController: NavHostController) {
    QuizzitTheme {
        Scaffold(
            topBar = {
                TopBar(name = "Score")
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.padding(30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Congratulations",
                        fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        modifier = Modifier.padding(20.dp),
                        painter = painterResource(id = R.drawable.trophy),
                        contentDescription = "Trophy Image"
                    )
                    Text(
                        text = "Your score is ${viewModel.totalScore}",
                        fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .padding(bottom = 30.dp),
                        lineHeight = 50.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    SubmitButton(text = "Finish") {
                        navController.navigate(Screen.Home.route)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreScreenPreview() {
    ScoreScreen(QuizViewModel(), NavHostController(LocalContext.current))
}