package com.example.cycles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cycles.ui.theme.CyclesTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CyclesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SumSinScreen(modifier: Modifier = Modifier){
    var nInput by remember { mutableStateOf("") }
    var resultText by remember {mutableStateOf("Результат появится здесь")}

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вычисление суммы: 1/sin(1) + 1/(sin(1)+sin(2)) + ...",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        OutlinedTextField(
            value = nInput,
            onValueChange = { newText ->
                // Разрешаем вводить только цифры
                if (newText.all { it.isDigit() }) {
                    nInput = newText
                }
            },
            label = { Text("Введите натуральное число n") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val n = nInput.toIntOrNull()
                if (n != null && n > 0) {
                    // Если число введено верно, считаем сумму
                    val sum = calculateSum(n)
                    resultText = "Сумма: ${String.format("%.5f", sum)}"
                } else {
                    resultText = "Пожалуйста, введите число больше 0"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Вычислить")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CyclesTheme {
        Greeting("Android")
    }
}