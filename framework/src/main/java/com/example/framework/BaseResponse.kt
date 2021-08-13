package com.example.framework

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("Message")
    var message: String = ""
}
