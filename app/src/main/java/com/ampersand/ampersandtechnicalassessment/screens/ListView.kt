package com.ampersand.ampersandtechnicalassessment.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ampersand.ampersandtechnicalassessment.data.ObjectData
import com.ampersand.ampersandtechnicalassessment.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListView(viewModel: MainViewModel, navController: NavController) {
    val objects by viewModel.objects.collectAsState()

    if (objects.isEmpty()) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(objects) { objectData ->
                ObjectItem(objectData = objectData, onItemClick = {
                    navController.navigate("detailsView/${objectData.id}")
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObjectItem(objectData: ObjectData, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onItemClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = objectData.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "ID: ${objectData.id}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}