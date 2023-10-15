package com.example.bookapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookapp.data.models.Book
import com.example.bookapp.data.models.Result
import com.example.bookapp.domain.usecases.AddBookDataUseCase
import com.example.bookapp.domain.usecases.DeleteBookDataUseCase
import com.example.bookapp.domain.usecases.GetBookDataUseCase
import com.example.bookapp.domain.usecases.UpdateBookDataUseCase
import com.example.bookapp.utils.SingletonObject.getAddBookDataUseCaseInstance
import com.example.bookapp.utils.SingletonObject.getBookDataUseCaseInstance
import com.example.bookapp.utils.SingletonObject.getDeleteBookDataUseCaseInstance
import com.example.bookapp.utils.SingletonObject.getUpdateBookDataUseCaseInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class BookViewModel() : ViewModel() {

    private var getBookDataUseCase: GetBookDataUseCase = getBookDataUseCaseInstance()
    private var addBookDataUseCase: AddBookDataUseCase = getAddBookDataUseCaseInstance()
    private var updateBookDataUseCase: UpdateBookDataUseCase = getUpdateBookDataUseCaseInstance()
    private var deleteBookDataUseCase: DeleteBookDataUseCase = getDeleteBookDataUseCaseInstance()

    val viewModelScope = CoroutineScope(Dispatchers.IO) + Job()

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList = _bookList as LiveData<List<Book>>

    private val _addBookResult = MutableLiveData<Book>()
    val addBookResult = _addBookResult as LiveData<Book>

    fun getBooks() {
        viewModelScope.launch {
            getBookDataUseCase.invoke(scope = this, params = Unit, onSuccess = {
                viewModelScope.launch {
                    it.collect(FlowCollector {
                        when (it) {
                            is Result.Error -> {}
                            Result.Loading -> {}
                            is Result.Success -> {
                                it.data?.let {
                                    _bookList.postValue(it)
                                }
                            }
                        }
                    })
                }
            }, onFailure = {

            }
            )
        }
    }

    suspend fun addBook(book: Book) =
        addBookDataUseCase.invoke(scope = viewModelScope, params = book, onSuccess = {
            viewModelScope.launch {
                it.collect(FlowCollector {
                    when (it) {
                        is Result.Error -> {}
                        Result.Loading -> {}
                        is Result.Success -> {
                            it.data?.let {
                                _addBookResult.postValue(it)
                            }
                        }
                    }
                })
            }
        })

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}