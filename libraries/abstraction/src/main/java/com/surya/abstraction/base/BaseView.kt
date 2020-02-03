package com.surya.abstraction.base

interface BaseView {
    fun onMessage(message: String?)
    fun onMessage(stringResId: Int)
    fun hideKeyboard()
}