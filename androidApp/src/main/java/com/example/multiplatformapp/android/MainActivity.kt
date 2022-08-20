package com.example.multiplatformapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.multiplatformapp.Greeting
import com.example.multiplatformapp.presentation.GithubViewModel

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val viewModel = remember { GithubViewModel("sduj") }
                val repos by viewModel.repositories.collectAsState()

                LazyColumn {
                    itemsIndexed(
                        items = repos,
                        itemContent = { _, item ->
                            Text(text = item.name.toString())
                        }
                    )
                }
            }
        }
    }
}