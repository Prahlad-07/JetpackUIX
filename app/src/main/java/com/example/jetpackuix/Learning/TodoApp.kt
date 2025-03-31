package com.example.jetpackuix.Learning

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoAppScreen() {
    var todos by remember { mutableStateOf(emptyList<Todo>()) }
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Todo List", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E1E2C))
            )
        },
        containerColor = Color(0xFF1E1E2C)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF1E1E2C)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Enter a task...", color = Color.Gray) },
                    textStyle = LocalTextStyle.current.copy(color = Color.White), // **Fix: White text**
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Cyan,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Cyan
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (text.isNotBlank()) {
                                todos = todos + Todo(text)
                                text = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Add Task", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = { todos = emptyList() }, // Clear all tasks
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Clear All", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(todos, key = { it.id }) { todo ->
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(animationSpec = tween(500)),
                            exit = fadeOut(animationSpec = tween(300))
                        ) {
                            TodoItem(todo = todo, onDelete = {
                                todos = todos - todo
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TodoItem(todo: Todo, onDelete: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A40)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onDelete() } // Tap to delete
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = todo.text,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )

            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(40.dp, 32.dp)
            ) {
                Text("X", fontSize = 14.sp)
            }
        }
    }
}

data class Todo(val text: String, val id: Int = text.hashCode())
