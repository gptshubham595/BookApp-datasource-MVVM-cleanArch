package com.example.bookapp.data.datasource.api

import com.example.bookapp.data.models.Book
import com.example.bookapp.utils.APIConstants
import retrofit2.http.GET

interface APIInterface {

    @GET(APIConstants.API_FETCH_DATA)
    suspend fun fetchData(
    ): List<Book>
}