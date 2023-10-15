package com.example.bookapp.domain.interfaces

import com.example.bookapp.data.models.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


abstract class IBaseUseCase<in Params, out Type> where Type : Any? {

    suspend fun invoke(
        scope: CoroutineScope,
        params: Params,
        onSuccess: (Type) -> Unit = {},
        onFailure: (Type) -> Unit = {},
    ) {
        val job = scope.async { run(params) }
        scope.launch {
            job.await().let {
                onSuccess(it)
            }
        }
    }

    abstract suspend fun run(params: Params): Type
}