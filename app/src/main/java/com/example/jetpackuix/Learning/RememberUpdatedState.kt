package com.example.jetpackuix.Learning

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun RememberUpdatedStateExample() {
    val counter = remember { mutableStateOf(0) } // Remember a state variable

    LaunchedEffect(Unit) { // Runs when the Composable enters composition
        Log.d("RememberUpdatedState", "LaunchedEffect started")
        delay(2000) // Simulating some delay (e.g., fetching data)
        counter.value = 10
    }

    CounterDisplay(counter.value) // UI function to show the counter
}

@Composable
fun CounterDisplay(value: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Dark theme background
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "⏳ RememberUpdatedState Example",
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Counter: $value",
            fontSize = 30.sp,
            color = Color.Cyan,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (value == 10) {
            Text(
                text = "✅ Value Updated After Delay!",
                fontSize = 18.sp,
                color = Color.Green,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
