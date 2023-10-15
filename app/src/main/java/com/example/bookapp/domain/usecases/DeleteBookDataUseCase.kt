package com.example.bookapp.domain.usecases

import com.example.bookapp.data.models.Book
import com.example.bookapp.data.models.Result
import com.example.bookapp.domain.interfaces.IBaseUseCase
import com.example.bookapp.domain.interfaces.IBookRepository
import kotlinx.coroutines.flow.Flow

class DeleteBookDataUseCase(private val iBookRepository: IBookRepository) :
    IBaseUseCase<Long, Flow<Result<Long>>>() {
    override suspend fun run(params: Long): Flow<Result<Long>> {
        return iBookRepository.removeBook(params)
    }
}

