package com.example.jetpackuix.Learning

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SideEffectExample() {
    val counter = remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope() // Used for manual coroutine launch

    LaunchedEffect(Unit) { // SIDE EFFECT: Runs when the Composable enters composition
        Log.d("SideEffectExample", "LaunchedEffect Started")
        try {
            for (i in 1..10) {
                counter.value++
                delay(1000) // Simulating work
            }
        } catch (e: Exception) {
            Log.d("Exception", "Error: ${e.message}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🔥 Side Effect Example 🔥",
            style = MaterialTheme.typography.headlineMedium // Correct Material3 typography usage
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Current Count: ${counter.value}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (counter.value == 10) {
            Text(
                text = "✅ Counter has stopped!",
                color = MaterialTheme.colorScheme.primary, // Corrected for Material3
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                coroutineScope.launch { // REMEMBER COROUTINE SCOPE: Launches a coroutine manually
                    counter.value = 0
                    for (i in 1..10) {
                        counter.value++
                        delay(1000)
                    }
                }
            }
        ) {
            Text("🔄 Restart Counter")
        }
    }
}
