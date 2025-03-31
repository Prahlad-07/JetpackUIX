package com.example.jetpackuix.Learning

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScopeExample() {
    val counter = remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope() // Remember a CoroutineScope for manual launching

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Dark theme
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🚀 CoroutineScope Example",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Counter: ${counter.value}",
            fontSize = 30.sp,
            color = Color.Cyan,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    counter.value = 0
                    for (i in 1..10) {
                        counter.value++
                        delay(1000)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)), // Corrected here!
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Start Counter", fontSize = 18.sp, color = Color.White)
        }

        if (counter.value == 10) {
            Text(
                text = "✅ Counter has stopped!",
                fontSize = 18.sp,
                color = Color.Green,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
