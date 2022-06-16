package com.example.kotlintutorial.UT

interface IAddToCart {
    fun addToCart(cartItem:CartItem):CartStatusResult
}

class CartItem(val orderId: String, val amount:Int)

enum class CartStatusResult {
    SUCCESS,
    AUTH_ERROR,
    SERVER_ERROR
}

