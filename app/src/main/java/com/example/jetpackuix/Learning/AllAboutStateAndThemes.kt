package com.example.jetpackuix.Learning

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackuix.ui.theme.JetpackUIXTheme
import kotlinx.coroutines.delay


//Louncher Effect-->it will only once ,untill we'll not change the value of it;
// it mainly use to run side effect kind of composesable
@Composable
fun counter(){
    var count= remember { mutableStateOf(value = 0) }
    var key=count.value%3==0
    LaunchedEffect(key1 = key) {
        Log.d("Counter","Current Value:${count.value}")

    }
    Button(onClick = {count.value++}) {
        Text("Button Clicked")
    }

}

@Composable
fun LauncherEffectExample() {
    val counter = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) { // Ensure the effect runs only once when the Composable enters composition
        Log.d("LauncherEffectExample", "Started")
        try {
            for (i in 1..10) {
                counter.value++
                delay(1000) // Delay inside coroutine scope
            }
        } catch (e: Exception) {
            Log.d("Exception Message", "Exception - ${e.message}")
        }
    }

    Column {
        Text(text = "Counter is now running") // Fixed text

        if (counter.value == 10) { // Corrected condition
            Text(text = "Counter has stopped")
        }

        Text(text = "Current count: ${counter.value}") // Display counter value
    }
}



@Composable
fun App() {
    var isDarkTheme by remember { mutableStateOf(false) }
    JetpackUIXTheme(isDarkTheme) {
        val backgroundColor by animateColorAsState(
            targetValue = if (isDarkTheme) Color(0xFF121212) else Color(0xFFFFFFFF),
            label = "Background Color Animation"
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = backgroundColor
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Theme: ${if (isDarkTheme) "Dark" else "Light"}",
                    style = TextStyle(

                        color = if (isDarkTheme) Color.White else Color.Black,
                        fontSize = 24.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { isDarkTheme = !isDarkTheme },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDarkTheme) Color.LightGray else Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Switch Theme",

                        color = if (isDarkTheme) Color.Black else Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun TimerExample(targetTime: Int) {
    LaunchedEffect(targetTime) { // Restarts whenever targetTime changes ❌
        delay(5000)
        Log.d("Timer", "Time's up! Target: $targetTime")
    }
}

