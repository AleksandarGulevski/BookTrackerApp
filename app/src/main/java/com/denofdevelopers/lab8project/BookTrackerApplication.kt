package com.denofdevelopers.lab8project

import android.app.Application
import com.denofdevelopers.lab8project.data.AppContainer
import com.denofdevelopers.lab8project.data.AppDataContainer

class BookTrackerApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}