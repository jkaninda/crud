package com.github.jkantech.crudexample.utils

import android.content.Context
import android.widget.Toast
import com.github.jkantech.crud.Serializer
import com.github.jkantech.crudexample.models.Users


fun toasMessage(context: Context, message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
    //Serialise

fun initUsers(jsonresponse: String?): Users? {
    return Serializer().fromJson(jsonresponse, Users::class.java)
}
