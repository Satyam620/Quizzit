package com.example.quizzit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzit.ui.model.QuizViewModel
import com.example.quizzit.ui.theme.Blue
import com.example.quizzit.ui.theme.lightBlue
import com.example.quizzit.ui.theme.mediumBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: QuizViewModel) {
    val colorStops = arrayOf(
        0.0f to lightBlue,
        0.8f to mediumBlue,
    )
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (viewModel.loginDialogue.value) {
        BasicAlertDialog(
            modifier = Modifier.background(Blue, shape = AlertDialogDefaults.shape),
            onDismissRequest = {
                viewModel.loginDialogue.value = false // Handle dismissing the dialog
            }, content = {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Login",
                        fontSize = 30.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    )
                    Button(
                        modifier = Modifier.background(
                            brush = Brush.horizontalGradient(colorStops = colorStops),
                            shape = ButtonDefaults.shape
                        ), onClick = {
                            // Handle the login action
                            if (email.isNotEmpty() && password.isNotEmpty()) {
                                // Perform login action here
                                viewModel.loginDialogue.value =
                                    false // Dismiss the dialog after login
                            }
                        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(text = "Login".uppercase(), fontSize = 15.sp, color = Color.White)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "If not created Account,",
                            textAlign = TextAlign.Center,
                            color = Color.White,
                        )
                        TextButton(
                            onClick = {
                                viewModel.loginDialogue.value = false
                                viewModel.registerDialogue.value = true
                            },
                        ) {
                            Text(
                                text = "Register",
                                textAlign = TextAlign.Center,
                                color = Color.Cyan,
                            )
                        }
                    }

                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(QuizViewModel())
}