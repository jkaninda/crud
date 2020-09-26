package com.github.jkantech.crud


interface OnResponseListener {
    fun onError(error: String?)
    fun onResponse(response: String?)

}

