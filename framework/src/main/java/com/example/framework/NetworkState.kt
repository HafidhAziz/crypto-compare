package com.example.framework

sealed class NetworkState {
    object SUCCESS : NetworkState()
    object ERROR : NetworkState()
}
