package com.denofdevelopers.lab8project.ui.book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.denofdevelopers.lab8project.BookTopAppBar
import com.denofdevelopers.lab8project.R
import com.denofdevelopers.lab8project.ui.booklist.BookListDestination
import com.denofdevelopers.lab8project.ui.navigation.NavigationDestination
import com.denofdevelopers.lab8project.ui.theme.Lab8ProjectTheme

object AddBookDestination : NavigationDestination {
    override val route = "add_book"
    override val titleRes = R.string.add_book
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: AddBookViewModel = viewModel()
) {
//    val uiState by viewModel.addBookUiState.collectAsState()
    Scaffold(
        topBar = {
            BookTopAppBar(
                title = stringResource(BookListDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        BookInputForm(
            onAddClick = {},
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}


@Composable
fun BookInputForm(onAddClick: () -> Unit, modifier: Modifier = Modifier) {

    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenuField(
            label = "Status",
            selectedItem = status,
            items = listOf("Read", "Currently Reading", "Want to Read"),
            onItemSelected = { selectedStatus ->
                status = selectedStatus
            }
        )

        RatingBar(
            rating = 5,
            onRatingChanged = { }
        )

        Button(
            onClick = { onAddClick },
//            enabled = uiState.isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Book")
        }
    }
}

@Composable
fun DropdownMenuField(
    label: String,
    selectedItem: String,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier.clickable { expanded = true }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        expanded = false
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}

@Composable
fun RatingBar(rating: Int, onRatingChanged: (Int) -> Unit) {
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Default.Star else Icons.Outlined.Star,
                contentDescription = null,
                modifier = Modifier.clickable { onRatingChanged(index + 1) }
            )
        }
    }
}

@Preview
@Composable
fun AddBookPreview() {
    Lab8ProjectTheme {
        AddBookScreen(
            navigateBack = {},
            onNavigateUp = {}
        )
    }
}