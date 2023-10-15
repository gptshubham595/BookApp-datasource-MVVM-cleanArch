package com.example.bookapp.domain.usecases

import com.example.bookapp.data.models.Book
import com.example.bookapp.data.models.Result
import com.example.bookapp.domain.interfaces.IBaseUseCase
import com.example.bookapp.domain.interfaces.IBookRepository
import kotlinx.coroutines.flow.Flow

class GetBookDataUseCase(private val iBookRepository: IBookRepository) :
    IBaseUseCase<Unit, Flow<Result<List<Book>>>>() {
    override suspend fun run(params: Unit): Flow<Result<List<Book>>> {
        return iBookRepository.getBooks()
    }
}

