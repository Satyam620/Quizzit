package com.example.quizzit

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.quizzit.ui.MainScreen
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.QuizzitTheme

class MainActivity : ComponentActivity() {
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Exit")
                .setMessage("Do you really want to exit?")
                .setPositiveButton("Yes") { _, _ -> finish() }
                .setNegativeButton("No", null)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizzitTheme {
                val viewModel: QuizViewModel = viewModel()
                val navController = rememberNavController()
                WindowCompat.setDecorFitsSystemWindows(window, false)
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                    false
                MainScreen(viewModel, navController)
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}
