package com.example.crypto_compare.data

import com.example.framework.BaseResponse
import com.google.gson.annotations.SerializedName

class WatchlistResponse : BaseResponse() {
    @SerializedName("Result")
    var metadata: Metadata? = null

    @SerializedName("CoinInfo")
    var coinInfo: CoinInfo? = null

    @SerializedName("DISPLAY")
    var display: Display? = null

    class Metadata {
        @SerializedName("Count")
        var count: Int? = 0
    }

    class CoinInfo {
        @SerializedName("Id")
        var id: String? = null
    }

    class Display {
        @SerializedName("USD")
        var usd: USD? = null
    }

    class USD {
        @SerializedName("FROMSYMBOL")
        var fromSymbol: String? = null
    }
}