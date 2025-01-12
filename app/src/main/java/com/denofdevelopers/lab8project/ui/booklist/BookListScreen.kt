package com.denofdevelopers.lab8project.ui.booklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.denofdevelopers.lab8project.BookTopAppBar
import com.denofdevelopers.lab8project.R
import com.denofdevelopers.lab8project.data.Book
import com.denofdevelopers.lab8project.ui.navigation.NavigationDestination

object BookListDestination : NavigationDestination {
    override val route = "book_list"
    override val titleRes = R.string.app_name
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    navigateToAddBook: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookListViewModel = viewModel()
) {
    //TODO Use UI state
    //    val uiState by viewModel.bookListUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val sampleBooks = listOf(
        Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "Reading", 5),
        Book(2, "1984", "George Orwell", "To Read", 4),
        Book(3, "To Kill a Mockingbird", "Harper Lee", "Finished", 3)
    )

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BookTopAppBar(
                title = stringResource(BookListDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToAddBook) {
                Icon(Icons.Default.Add, contentDescription = "Add Book")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleBooks) { book ->
                BookItem(book) { }
            }
        }
    }
}

@Composable
fun BookItem(
    book: Book,
    onClick: (Book) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(book) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Padding for the content inside the Card
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Title: ${book.title}", style = MaterialTheme.typography.titleLarge)
                Text(text = "Author: ${book.author}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Status: ${book.status}", style = MaterialTheme.typography.bodyLarge)
                RatingBar(rating = book.rating)
            }

            IconButton(
                onClick = { showDialog = true },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = Color.Red,
                    contentDescription = "Remove Book"
                )
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Confirm Deletion") },
                    text = { Text("Are you sure you want to remove this book?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                //remove book
                                showDialog = false
                            }
                        ) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog = false }
                        ) {
                            Text("No")
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun RatingBar(rating: Int, maxRating: Int = 5) {
    Row {
        repeat(maxRating) { index ->
            Icon(
                painter = if (index < rating) painterResource(R.drawable.star) else {
                    painterResource(R.drawable.star_outline)
                },
                tint = Color.Yellow,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBookListScreen() {
    BookListScreen(
        navigateToAddBook = {}
    )
}
