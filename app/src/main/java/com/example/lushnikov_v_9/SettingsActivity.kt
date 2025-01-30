package com.example.lushnikov_v_9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lushnikov_v_9.ui.theme.Lushnikov_v_9Theme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lushnikov_v_9Theme {
                SettingsScreen(onBackClicked = { finish() })
            }
        }
    }
}

data class HistoryItem(val title: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBackClicked: () -> Unit) {
    // «История сканирований» - список неизменяемых записей, только удалять
    val historyItems = remember {
        mutableStateListOf(
            HistoryItem("Телефон: +7 999 548-84-28"),
            HistoryItem("URL: https://qwe.gif.com/cut"),
            HistoryItem("Описание: Сегодня будет урок по Git")
        )
    }

    // Чекбоксы (настройки)
    var rememberData by remember { mutableStateOf(true) }
    var saveActions by remember { mutableStateOf(true) }
    var statistics by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("История / Настройки") },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("История сканирований:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Список с кнопками "Удалить"
            LazyColumn {
                items(historyItems) { item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item.title, modifier = Modifier.weight(1f))
                        Button(
                            onClick = { historyItems.remove(item) },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text("Удалить")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Divider()

            // «Настройки» (чекбоксы)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Checkbox(checked = rememberData, onCheckedChange = { rememberData = it })
                Text("Запомнить мои данные", modifier = Modifier.padding(top = 12.dp))
            }
            Row {
                Checkbox(checked = saveActions, onCheckedChange = { saveActions = it })
                Text("Сохранить мои действия", modifier = Modifier.padding(top = 12.dp))
            }
            Row {
                Checkbox(checked = statistics, onCheckedChange = { statistics = it })
                Text("Вести статистику сканирований", modifier = Modifier.padding(top = 12.dp))
            }
        }
    }
}
