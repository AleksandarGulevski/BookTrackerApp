package com.denofdevelopers.lab8project.ui.booklist

import androidx.lifecycle.ViewModel
import com.denofdevelopers.lab8project.data.Book
import kotlinx.coroutines.flow.StateFlow

class BookListViewModel : ViewModel() {

    val bookListUiState : StateFlow<BookListUiState>? = null

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


}

data class BookListUiState(
    val bookList: List<Book> = listOf()
)