package com.example.multiplatformapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import com.example.multiplatformapp.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Text(text = "stanko")
            }
        }
    }
}