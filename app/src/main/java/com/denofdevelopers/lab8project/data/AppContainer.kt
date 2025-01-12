package com.denofdevelopers.lab8project.data

import android.content.Context

interface AppContainer {
    val bookRepository: BookRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val bookRepository: BookRepository by lazy {
        OfflineBookRepository()
    }

}