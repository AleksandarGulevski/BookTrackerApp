package com.denofdevelopers.lab8project.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.denofdevelopers.lab8project.ui.book.AddBookDestination
import com.denofdevelopers.lab8project.ui.book.AddBookScreen
import com.denofdevelopers.lab8project.ui.booklist.BookListDestination
import com.denofdevelopers.lab8project.ui.booklist.BookListScreen

@Composable
fun BookNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BookListDestination.route,
        modifier = modifier
    ) {
        composable(route = BookListDestination.route) {
            BookListScreen(
                navigateToAddBook = { navController.navigate(AddBookDestination.route) },
            )
        }
        composable(route = AddBookDestination.route) {
            AddBookScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

    }
}