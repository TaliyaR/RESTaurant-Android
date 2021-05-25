package com.example.restaurant.presenter

import com.example.restaurant.data.network.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import moxy.MvpPresenter

abstract class BasePresenter<V : BaseView> : MvpPresenter<V>(),
    CoroutineScope by CoroutineScope(Dispatchers.Main) {

    val messageFlow: MutableSharedFlow<String> = MutableSharedFlow()

    protected fun <T : Any> handleResult(
        result: ApiResult<T>, success: (success: ApiResult.Success<T>) -> Unit,
        error: (error: ApiResult.Error) -> Unit
    ) {
        if (result is ApiResult.Success) {
            success(result)
        } else if (result is ApiResult.Error) {
            error(result)
        }
    }

    protected fun handleError(error: ApiResult.Error) {
        viewState.showMessage(error.throwable.message ?: "Error")
    }
}