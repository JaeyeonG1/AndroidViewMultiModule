package com.tambi.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Base class for [ViewModel]s that need event.
 * Consumer can collect event by collect [eventFlow].
 *
 * @param T By use sealed interface, event type [T] can be restricted when used by consumers.
 */
open class BaseViewModel<T> : ViewModel() {

    /** A backing field for providing an immutable [eventFlow] property. */
    private val _eventFlow = MutableSharedFlow<T>()

    /** Consumer can trigger event by collect this field. */
    val eventFlow get() = _eventFlow.asSharedFlow()

    /**
     * Function for generating events
     * @param event Predefined events in [BaseViewModel]
     */
    protected fun event(event: T) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}
