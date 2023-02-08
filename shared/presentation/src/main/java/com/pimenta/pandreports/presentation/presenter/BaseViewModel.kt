package com.pimenta.pandreports.presentation.presenter

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<ViewState, ViewEvent> : ViewModel() {

    private val stateObservable = BehaviorSubject.create<ViewState>()
    private val eventObservable = PublishSubject.create<ViewEvent>()

    protected val compositeDisposable = CompositeDisposable()
    protected val currentState: ViewState
        get() = stateObservable.blockingFirst()

    val state: Observable<ViewState>
        get() = stateObservable.hide()

    val events: Observable<ViewEvent>
        get() = eventObservable.hide()

    protected fun emitEvent(event: ViewEvent) {
        eventObservable.onNext(event)
    }

    protected fun emitState(state: ViewState) {
        stateObservable.onNext(state)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
