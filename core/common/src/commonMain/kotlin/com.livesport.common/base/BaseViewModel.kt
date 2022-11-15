package com.livesport.common.base

import com.chyrta.livesport.common.base.UiEffect
import com.chyrta.livesport.common.base.UiEvent
import com.chyrta.livesport.common.base.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<Event: UiEvent, State: UiState, Effect: UiEffect> {

    private val initialState: State by lazy { createInitialState() }

    abstract fun createInitialState(): State

    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {

    }
}
