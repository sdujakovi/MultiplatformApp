package com.example.multiplatformapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multiplatformapp.presentation.GithubViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val viewModel = remember { GithubViewModel("sdu") }
                val repos by viewModel.repositories.collectAsState()
                val loading by viewModel.anyUseCaseInProgress.collectAsState()
                val systemUiController = rememberSystemUiController()

                systemUiController.setStatusBarColor(
                    color = Color(0xFFFFF9FC)
                )

                Column(
                    modifier = Modifier
                        .background(Color(0xFFFFF9FC))
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Search a Repository",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 15.dp),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    SearchBar(onSearchButtonClicked = { searchText ->
                        viewModel.getGithubRepositories(searchText)
                    })
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 100.dp, vertical = 160.dp),
                            strokeWidth = 15.dp,
                            color = Color(0xFFefdff5)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            itemsIndexed(
                                items = repos,
                                itemContent = { _, item ->
                                    Item(repository = item)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(onSearchButtonClicked: (String) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }

    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(bottom = 15.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                TrailingIcon() { onSearchButtonClicked(searchText) }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFefdff5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(text = "Search your repo...") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}

@Composable
fun TrailingIcon(onSearchButtonClicked: () -> Unit) {
    IconButton(
        onClick = onSearchButtonClicked,
    ) {
        Icon(
            Icons.Default.Search,
            contentDescription = "",
            tint = Color(0xFF554859)
        )
    }
}