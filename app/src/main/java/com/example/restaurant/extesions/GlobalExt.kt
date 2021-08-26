package com.example.restaurant.extesions

inline fun <T> tryOrNull(block: () -> T?): T? {
    return try {
        block()
    } catch (e: Throwable) {
        null
    }
}