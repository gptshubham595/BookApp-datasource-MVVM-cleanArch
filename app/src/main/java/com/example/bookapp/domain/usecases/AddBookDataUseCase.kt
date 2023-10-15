package com.example.bookapp.domain.usecases

import com.example.bookapp.data.models.Book
import com.example.bookapp.data.models.Result
import com.example.bookapp.domain.interfaces.IBaseUseCase
import com.example.bookapp.domain.interfaces.IBookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class AddBookDataUseCase(private val iBookRepository: IBookRepository) :
    IBaseUseCase<Book, Flow<Result<Book>>>() {

    override suspend fun run(params: Book): Flow<Result<Book>> {
        return iBookRepository.addBook(params)
    }
}