package com.example.multiplatformapp.data.utils

import kotlinx.coroutines.CoroutineScope

expect abstract class MainDispatcher() {
    val coroutineScope: CoroutineScope

    fun dispose()

    protected open fun onCleared()
}