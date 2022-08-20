package com.example.multiplatformapp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}