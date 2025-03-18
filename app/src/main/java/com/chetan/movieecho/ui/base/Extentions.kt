package com.chetan.movieecho.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest


@Composable
inline fun <reified STATE, EVENT, EFFECT> use(viewmodel: UViewModel<STATE, EVENT, EFFECT>): StateEventEffect<STATE, EVENT, EFFECT> {
    val state: State<STATE> = (viewmodel.state as StateFlow<STATE>).collectAsStateWithLifecycle(initial = viewmodel.state.value)
    val event: (EVENT) -> Unit = remember {
        { event ->
            viewmodel.event(event)
        }
    }
    return StateEventEffect(state, event, viewmodel.effect)
}
@Composable
fun <T> SharedFlow<T>.collectInLaunchedEffect(function : suspend (value : T) -> Unit){
    val sharedFlow = this
    LaunchedEffect(key1 = sharedFlow){
        sharedFlow.collectLatest(function)
    }
}

@Composable
fun <T> Flow<T>.collectAsStateWithLifecycle(
    initial: T,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = androidx.lifecycle.Lifecycle.State.STARTED
): State<T> {
    val lifecycleAwareStateFlow = remember(this, lifecycleOwner) {
        flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
    }
    return lifecycleAwareStateFlow.collectAsState(initial)
}

