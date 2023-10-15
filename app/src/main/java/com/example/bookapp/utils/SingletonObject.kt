package com.example.bookapp.utils

import com.example.bookapp.data.datasource.DataSource
import com.example.bookapp.data.datasource.api.APIInterface
import com.example.bookapp.data.repositories.BookRepositoryImpl
import com.example.bookapp.domain.usecases.AddBookDataUseCase
import com.example.bookapp.domain.usecases.DeleteBookDataUseCase
import com.example.bookapp.domain.usecases.GetBookDataUseCase
import com.example.bookapp.domain.usecases.UpdateBookDataUseCase
import com.example.bookapp.utils.APIConstants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SingletonObject {

    private var repositoryInstance: BookRepositoryImpl? = null
    private var getBookDataUseCaseInstance: GetBookDataUseCase? = null
    private var addBookDataUseCaseInstance: AddBookDataUseCase? = null
    private var updateBookDataUseCaseInstance: UpdateBookDataUseCase? = null
    private var deleteBookDataUseCaseInstance: DeleteBookDataUseCase? = null
    private var apiInterface: APIInterface? = null

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build()

    fun getAPIInterfaceInstance(): APIInterface {
        return retrofit.create(APIInterface::class.java)
    }

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