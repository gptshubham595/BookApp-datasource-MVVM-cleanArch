package com.example.bookapp.data.repositories

import com.example.bookapp.data.datasource.DataSource
import com.example.bookapp.data.models.Book
import com.example.bookapp.data.models.Result
import com.example.bookapp.domain.interfaces.IBookRepository
import com.example.bookapp.utils.SingletonObject.getAPIInterfaceInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

class BookRepositoryImpl constructor(private val dataSource: DataSource) : IBookRepository {

    private val apiInterface = getAPIInterfaceInstance()
    override suspend fun getBooks(): Flow<Result<List<Book>>> = flow<Result<List<Book>>> {
//        apiInterface.fetchData()
        emit(Result.Success(dataSource.getBooks()))
    }.onStart { emit(Result.Loading) }.flowOn(Dispatchers.IO)

    override suspend fun addBook(book: Book): Flow<Result<Book>> = flow<Result<Book>> {
        val isAdded = dataSource.addBook(book)
        if (isAdded) {
            emit(Result.Success(book))
        } else {
            emit(Result.Error(Exception("Error while adding book")))
        }
    }.onStart { emit(Result.Loading) }.catch { emit(Result.Error(it)) }.flowOn(Dispatchers.IO)

    override suspend fun updateBook(book: Book): Flow<Result<Book>> = flow {
        val updatedBook = dataSource.updateBook(book)
        updatedBook?.let {
            emit(Result.Success(it))
        } ?: emit(Result.Error(Exception("Book not found")))
    }.onStart { emit(Result.Loading) }.catch { emit(Result.Error(it)) }.flowOn(Dispatchers.IO)

    override suspend fun removeBook(bookId: Long): Flow<Result<Long>> = flow<Result<Long>> {
        val isBookRemoved = dataSource.removeBook(bookId)
        if (isBookRemoved)
            emit(Result.Success(bookId))
        else
            emit(Result.Error(Exception("Book not found")))
    }.onStart { emit(Result.Loading) }.catch { emit(Result.Error(it)) }.flowOn(Dispatchers.IO)
}