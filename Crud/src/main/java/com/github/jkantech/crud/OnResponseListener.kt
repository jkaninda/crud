package com.github.jkantech.crud


interface OnResponseListener {
    fun onResponse(response: String?)
    fun onError(error: String?)
}

