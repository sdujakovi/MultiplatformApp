package com.example.multiplatformapp.data.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual abstract class MainDispatcher {
    actual val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    protected actual open fun onCleared() {}
}