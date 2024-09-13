package com.example.quizzit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizzit.ui.model.LeaderBoardItem
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.Blue
import com.example.quizzit.ui.theme.Purple
import com.example.quizzit.ui.theme.Red

@Composable
fun LeaderBoardScreen(viewModel: QuizViewModel, navController: NavHostController) {
    // Gradient colors for the background
    val backgroundGradient = Brush.horizontalGradient(0.0f to Red, 0.8f to Purple)

    val colorStops2 = arrayOf(
        0.0f to Purple,
        0.8f to Color.Red,
    )

    Scaffold(
        topBar = { TopBar("Leaderboard") },
        bottomBar = { BottomBar("Leaderboard", navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(backgroundGradient)
                .fillMaxSize()
        ) {
            //TODO Change the value inside the function.
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp)
                    .background(Brush.horizontalGradient(colorStops = colorStops2), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "RANK",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    maxLines = 1
                )
                Text(
                    text = "NAME",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    maxLines = 1
                )
                Text(
                    text = "SCORE",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    maxLines = 1
                )
            }
            LeaderboardList(viewModel.leaderBoardItems)
        }
    }
}

@Composable
fun LeaderboardList(leaderboard: List<LeaderBoardItem>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(leaderboard) { item ->
            LeaderboardColumn(
                rank = item.rank.toString(),
                name = item.name,
                score = item.score.toString()
            )
        }
    }
}

@Composable
fun LeaderboardColumn(rank: String, name: String, score: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .background(Blue, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = rank,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1
        )
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            maxLines = 1
        )
        Text(
            text = score,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun LeaderboardPreview() {
    LeaderBoardScreen(QuizViewModel(), NavHostController(LocalContext.current))
}
