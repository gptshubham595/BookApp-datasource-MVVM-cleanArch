package com.example.bookapp.data.datasource

import com.example.bookapp.data.models.Book
import java.util.Collections

object DataSource {
    private val books = Collections.synchronizedList(mutableListOf<Book>())

    init {
        books.add(Book(1, "The Great Gatsby", "F. Scott Fitzgerald"))
    }

    fun addBook(book: Book): Boolean {
        return books.add(book)
    }

    fun getBooks(): List<Book> {
        return books.toList()
    }

    fun updateBook(book: Book): Book? {
        val iterator = books.iterator()
        var currentBook: Book? = null
        while (iterator.hasNext()) {
            currentBook = iterator.next()
            if (currentBook.id == book.id) {
                currentBook.title = book.title
                currentBook.author = book.author
                break
            }
        }
        return currentBook
    }

    fun removeBook(bookId: Long): Boolean {
        return books.removeIf { it.id == bookId }
    }
}