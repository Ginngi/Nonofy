package com.nonofy.game.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

abstract class Performer<PARAM : Any, EFFECT : Any> {
    // Ideally this would be buffer = 0, since we use flatMapLatest below, BUT invoke is not
    // suspending. This means that we can't suspend while flatMapLatest cancels any
    // existing flows. The buffer of 1 means that we can use tryEmit() and buffer the value
    // instead, resulting in mostly the same result.
    private val paramState = MutableSharedFlow<PARAM>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    @ExperimentalCoroutinesApi
    val flow: Flow<EFFECT> = paramState
        .distinctUntilChanged()
        .flatMapLatest { createObservable(it) }
        .distinctUntilChanged()

    operator fun invoke(params: PARAM) {
        paramState.tryEmit(params)
    }

    protected abstract fun createObservable(params: PARAM): Flow<EFFECT>
}

interface Reducer<EFFECT, MODEL> {
    fun reduce(effect: EFFECT, oldModel: MODEL): MODEL
}

abstract class Feature<MODEL, EVENT, EFFECT>(
    vararg actionPerformers: Flow<EFFECT>,
    private val reducer: Reducer<EFFECT, MODEL>,
    scope: CoroutineScope,
    initialValue: MODEL
) {
    private val _model: Flow<MODEL> = actionPerformers.asIterable()
        .merge()
        .map { reducer.reduce(it, model.value) }

    val model: StateFlow<MODEL> = _model
        .stateIn(scope, SharingStarted.Lazily, initialValue)

    abstract fun onEvent(event: EVENT)
}