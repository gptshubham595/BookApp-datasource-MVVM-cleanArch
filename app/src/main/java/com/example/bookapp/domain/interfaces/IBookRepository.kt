package com.example.bookapp.domain.interfaces

import com.example.bookapp.data.models.Book
import com.example.bookapp.data.models.Result
import kotlinx.coroutines.flow.Flow

interface IBookRepository {

    suspend fun getBooks(): Flow<Result<List<Book>>>

    suspend fun addBook(book: Book): Flow<Result<Book>>

    suspend fun updateBook(book: Book): Flow<Result<Book>>

    suspend fun removeBook(bookId: Long): Flow<Result<Long>>
}