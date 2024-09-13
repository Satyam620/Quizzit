package com.example.quizzit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.Purple
import com.example.quizzit.ui.theme.Red
import com.example.quizzit.ui.theme.lightBlue
import com.example.quizzit.ui.theme.mediumBlue

@Composable
fun ProfileScreen(viewModel: QuizViewModel, navController: NavHostController) {
    val colorStops = arrayOf(
        0.0f to Red,
        0.8f to Purple,
    )
    val colorStops2 = arrayOf(
        0.0f to lightBlue,
        0.8f to mediumBlue,
    )

    Scaffold(
        topBar = {
            TopBar("Profile")
        },
        bottomBar = {
            BottomBar("Profile", navController)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Brush.horizontalGradient(colorStops = colorStops))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginScreen(viewModel = viewModel)
            RegisterScreen(viewModel = viewModel)
            if (viewModel.login.value) {
                Row {
                    //TODO Write profile page.
                }
            } else {
                Text(
                    text = "Please Login First",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 50.dp),
                    lineHeight = 50.sp
                )
                Button(
                    modifier = Modifier.background(
                        brush = Brush.horizontalGradient(colorStops = colorStops2),
                        shape = ButtonDefaults.shape
                    ), onClick = {
                        viewModel.loginDialogue.value = true
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "Login Now".uppercase(), fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview(){
    ProfileScreen(viewModel = QuizViewModel(), navController = NavHostController(LocalContext.current))
}
