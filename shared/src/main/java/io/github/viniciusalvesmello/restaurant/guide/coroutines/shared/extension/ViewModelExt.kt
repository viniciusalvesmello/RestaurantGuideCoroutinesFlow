package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launchMain(block: suspend () -> Unit) =
    this.viewModelScope.launch(Dispatchers.Main) {
        block()
    }

fun ViewModel.launchDefault(block: suspend () -> Unit) =
    this.viewModelScope.launch(Dispatchers.Default) {
        block()
    }

fun ViewModel.launchIO(block: suspend () -> Unit) =
    this.viewModelScope.launch(Dispatchers.IO) {
        block()
    }

