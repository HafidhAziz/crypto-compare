package com.example.framework

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class  BaseViewModel : ViewModel(), CoroutineScope {

    private val parentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext = parentJob + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}