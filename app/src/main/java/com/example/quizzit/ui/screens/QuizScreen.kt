package com.example.quizzit.ui.screens

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizzit.ui.Screen
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.QuizzitTheme

@Composable
fun QuizScreen(viewModel: QuizViewModel, navController: NavHostController) {
    val optionColor = MaterialTheme.colorScheme.tertiaryContainer
    val selectedOpt = MaterialTheme.colorScheme.surface
    val buttonColors =
        remember { mutableStateListOf(optionColor, optionColor, optionColor, optionColor) }


    fun updateButtonColors(selectedIndex: Int) {
        buttonColors.fill(optionColor)
        buttonColors[selectedIndex] = selectedOpt// Highlight the selected button
    }

    fun decodeHtml(encodedString: String): String {
        return Html.fromHtml(encodedString, Html.FROM_HTML_MODE_LEGACY).toString()
    }

    var selectedOption: String? = null

    Scaffold(
        topBar = { TopBar("Quiz-Time") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                viewModel.questionsState.value.loading -> {
                    CircularProgressIndicator(
                        strokeWidth = 10.dp,
                        modifier = Modifier.fillMaxSize(.7F),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                viewModel.questionsState.value.error != null -> {
                    Text(text = "ERROR OCCURRED", color = Color.Red, fontSize = 25.sp)
                }

                else -> {
                    Text(
                        modifier = Modifier.padding(30.dp, 20.dp, 30.dp, 10.dp),
                        text = decodeHtml(viewModel.question.value),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        lineHeight = 25.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        LinearProgressIndicator(
                            progress = { viewModel.currentProgress.floatValue },
                            modifier = Modifier
                                .fillMaxWidth(.7F)
                                .padding(end = 10.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            trackColor = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = viewModel.progress.value,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxHeight(.8f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        // Generate Option Buttons Dynamically
                        viewModel.options.value.forEachIndexed { index, item ->
                            Button(
                                onClick = {
                                    updateButtonColors(index)
                                    selectedOption = item
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = ButtonDefaults.shape,
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = buttonColors[index],
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                            ) {
                                Text(text = decodeHtml(item), fontSize = 20.sp, lineHeight = 25.sp)
                            }
                        }
                        // NEXT Button
                        SubmitButton(text = "Next") {
                            buttonColors.fill(optionColor)
                            viewModel.updateQuestion(selectedOption)
                            if (viewModel.navigateToScore.value) {
                                navController.navigate(Screen.Score.route)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizzitTheme {
        val viewModel = QuizViewModel()
        val context = LocalContext.current
        QuizScreen(viewModel, NavHostController(context))
    }
}