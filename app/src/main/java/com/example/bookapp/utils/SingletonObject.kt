package com.example.bookapp.utils

import com.example.bookapp.data.datasource.DataSource
import com.example.bookapp.data.repositories.BookRepositoryImpl
import com.example.bookapp.domain.usecases.AddBookDataUseCase
import com.example.bookapp.domain.usecases.DeleteBookDataUseCase
import com.example.bookapp.domain.usecases.GetBookDataUseCase
import com.example.bookapp.domain.usecases.UpdateBookDataUseCase

object SingletonObject {

    private var repositoryInstance: BookRepositoryImpl? = null
    private var getBookDataUseCaseInstance: GetBookDataUseCase? = null
    private var addBookDataUseCaseInstance: AddBookDataUseCase? = null
    private var updateBookDataUseCaseInstance: UpdateBookDataUseCase? = null
    private var deleteBookDataUseCaseInstance: DeleteBookDataUseCase? = null

    fun getRepositoryInstance(): BookRepositoryImpl {
        if (repositoryInstance == null) {
            repositoryInstance = BookRepositoryImpl(dataSource = DataSource)
        }

        return repositoryInstance as BookRepositoryImpl
    }


    fun getBookDataUseCaseInstance(): GetBookDataUseCase {
        if (getBookDataUseCaseInstance == null) {
            getBookDataUseCaseInstance =
                GetBookDataUseCase(iBookRepository = getRepositoryInstance())
        }

        return getBookDataUseCaseInstance as GetBookDataUseCase
    }

    fun getAddBookDataUseCaseInstance(): AddBookDataUseCase {
        if (addBookDataUseCaseInstance == null) {
            addBookDataUseCaseInstance =
                AddBookDataUseCase(iBookRepository = getRepositoryInstance())
        }

        return addBookDataUseCaseInstance as AddBookDataUseCase
    }

    fun getUpdateBookDataUseCaseInstance(): UpdateBookDataUseCase {
        if (updateBookDataUseCaseInstance == null) {
            updateBookDataUseCaseInstance =
                UpdateBookDataUseCase(iBookRepository = getRepositoryInstance())
        }

        return updateBookDataUseCaseInstance as UpdateBookDataUseCase
    }

    fun getDeleteBookDataUseCaseInstance(): DeleteBookDataUseCase {
        if (deleteBookDataUseCaseInstance == null) {
            deleteBookDataUseCaseInstance =
                DeleteBookDataUseCase(iBookRepository = getRepositoryInstance())
        }

        return deleteBookDataUseCaseInstance as DeleteBookDataUseCase
    }
}