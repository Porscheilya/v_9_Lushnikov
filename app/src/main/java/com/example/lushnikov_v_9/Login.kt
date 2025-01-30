package com.example.lushnikov_v_9

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lushnikov_v_9.ui.theme.Lushnikov_v_9Theme

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lushnikov_v_9Theme {
                // Экран логина
                LoginScreen { userName ->
                    // Переход в личный кабинет
                    val intent = Intent(this, PersonalArea::class.java)
                    intent.putExtra("USER_NAME", userName)
                    startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginClicked: (String) -> Unit) {
    // Состояния для полей
    var loginText by remember { mutableStateOf(TextFieldValue("")) }
    var passwordText by remember { mutableStateOf(TextFieldValue("")) }

    // Размещаем всё в колонке
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // КАРТИНКА (Добавьте файл ic_login.png в res/drawable)
        Image(
            painter = painterResource(id = R.drawable.ic_login),
            contentDescription = "Login Illustration",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = loginText,
            onValueChange = { loginText = it },
            label = { Text("Логин") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = passwordText,
            onValueChange = { passwordText = it },
            label = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onLoginClicked(loginText.text) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Авторизоваться")
        }
    }
}
