package com.chetan.movieecho.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface ViewSideEffect

const val SIDE_EFFECTS_KEY = "side-effects-key"
abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect> : ViewModel(){
    abstract fun setInitialState(): UiState
    abstract fun handleEvents(event: Event)
    private val initialState : UiState by lazy{
        setInitialState()
    }

    private val _viewState : MutableState<UiState> = mutableStateOf(initialState)
    val viewState : State<UiState> = _viewState

    private val _event : MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect : Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents(){
        viewModelScope.launch {
            _event.collect{
                handleEvents(it)
            }
        }
    }

    fun setEvent(event: Event){
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    protected fun setState( reducer : UiState.() -> UiState){
        val updatedUiState = viewState.value.reducer()
        _viewState.value = updatedUiState
    }

    protected fun setEffect(builder: () -> Effect){
        val effectValue = builder()
        viewModelScope.launch {
            _effect.send(effectValue)
        }

    }
}

interface UViewModel<STATE, EVENT, EFFECT>{
    val state : StateFlow<STATE>
    val effect : SharedFlow<EFFECT>
    fun event(event : EVENT)
}

//data class StateDispatchEffect<STATE, EVENT, EFFECT>(
//    val state: State<STATE>,
//    val dispatch: (EVENT) -> Unit,
//    val effect: SharedFlow<EFFECT>
//)

