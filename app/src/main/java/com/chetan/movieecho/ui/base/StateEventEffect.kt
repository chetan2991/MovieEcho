package com.chetan.movieecho.ui.base

import androidx.compose.runtime.State
import kotlinx.coroutines.flow.SharedFlow

 class StateEventEffect<STATE, EVENT, EFFECT>(
     val state: State<STATE>,
     val event: (EVENT) -> Unit,
     val effect: SharedFlow<EFFECT>
) {
    operator fun component1(): STATE = state.value
    operator fun component2(): (EVENT) -> Unit = event
    operator fun component3(): SharedFlow<EFFECT> = effect
}