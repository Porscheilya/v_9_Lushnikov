package com.example.lushnikov_v_9

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.example.lushnikov_v_9.ui.theme.Lushnikov_v_9Theme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

class PersonalAreaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userName = intent.getStringExtra("USER_NAME") ?: "User"

        setContent {
            Lushnikov_v_9Theme {
                PersonalAreaScreen(
                    userName = userName,
                    onBackClicked = { finish() },
                    onScanClicked = {
                        startActivity(Intent(this, ScannerActivity::class.java))
                    },
                    onSettingsClicked = {
                        startActivity(Intent(this, SettingsActivity::class.java))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalAreaScreen(
    userName: String,
    onBackClicked: () -> Unit,
    onScanClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Личный кабинет") },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { paddingValues ->
        // Основное содержимое
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "Привет, $userName!", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Мой QR-code: 20", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Избранное: 47", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onScanClicked) {
                Text("Начать сканирование")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onSettingsClicked) {
                Text("Настройки / История")
            }
        }
    }
}
