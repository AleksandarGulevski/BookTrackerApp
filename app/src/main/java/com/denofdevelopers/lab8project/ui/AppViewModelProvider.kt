package com.denofdevelopers.lab8project.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.denofdevelopers.lab8project.BookTrackerApplication
import com.denofdevelopers.lab8project.ui.book.AddBookViewModel
import com.denofdevelopers.lab8project.ui.booklist.BookListViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            BookListViewModel()
        }

        initializer {
            AddBookViewModel()
        }
    }
}

fun CreationExtras.inventoryApplication(): BookTrackerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as BookTrackerApplication)