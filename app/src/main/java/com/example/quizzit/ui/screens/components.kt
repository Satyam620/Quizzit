package com.example.quizzit.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import com.example.quizzit.ui.theme.QuizzitTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(name: String) {
    QuizzitTheme {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            title = {
                Text(
                    textAlign = TextAlign.Center,
                    text = name.uppercase(),
                    fontSize = 40.sp,
                    modifier = Modifier.fillMaxWidth(.96F),
                    fontWeight = FontWeight.ExtraBold
                )
            },
        )
    }
}

data class NavItem(val label: String, val route: String, val icon: Int, val index: Int)


@Composable
fun BottomBar(name: String, navController: NavHostController) {
    QuizzitTheme {
        val iconSize = 40.dp
        val iconColor = MaterialTheme.colorScheme.onTertiary
        val selectedIconColor = MaterialTheme.colorScheme.onSecondary
        val buttonColors =
            remember { mutableStateListOf(iconColor, iconColor, iconColor, iconColor) }

        val navItems = listOf(
            NavItem("Home", Screen.Home.route, R.drawable.outline_home_24, 0),
            NavItem("Category", Screen.Categories.route, R.drawable.outline_category_24, 1),
            NavItem("Leaderboard", Screen.Leaderboard.route, R.drawable.rounded_leaderboard_24, 2),
            NavItem("Profile", Screen.Profile.route, R.drawable.outline_person_24, 3)
        )
        val enabled = remember { mutableStateListOf(true, true, true, true) }

        fun updateButtonColors(selectedIndex: Int) {
            buttonColors.fill(iconColor) // Reset all to white
            buttonColors[selectedIndex] = selectedIconColor// Highlight the selected button
            enabled[selectedIndex] = false
        }

        navItems.firstOrNull { it.label == name }?.let { updateButtonColors(it.index) }
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.size(10.dp))
                navItems.forEach { item ->
                    IconButton(
                        onClick = {
                            navController.navigate(item.route)
                            updateButtonColors(item.index)
                        },
                        enabled = enabled[item.index]
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "${item.label} Button",
                            modifier = Modifier.size(iconSize),
                            tint = buttonColors[item.index]
                        )
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
fun SubmitButton(text: String, onClick: () -> Unit) {
    QuizzitTheme {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(3.dp, MaterialTheme.colorScheme.onSecondary, RoundedCornerShape(50.dp)),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = text.uppercase(), fontSize = 25.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopPreview() {
    TopBar("Home")
}

@Preview(showBackground = true)
@Composable
fun BottomPreview() {
    val context = LocalContext.current
    BottomBar(name = "Home", navController = NavHostController(context))
}

@Preview(showBackground = true)
@Composable
fun SubmitPreview() {
    SubmitButton("Submit") {

    }
}

