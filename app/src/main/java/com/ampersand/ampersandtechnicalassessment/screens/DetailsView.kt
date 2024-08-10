package com.ampersand.ampersandtechnicalassessment.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ampersand.ampersandtechnicalassessment.viewmodel.MainViewModel

@Composable
fun DetailsView(objectId: String?, viewModel: MainViewModel) {
    val objects by viewModel.objects.collectAsState()
    val objectData = objects.find { it.id == objectId }

    if (objectData != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = objectData.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "ID: ${objectData.id}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            if (objectData.data != null) {
                objectData.data.forEach { (key, value) ->
                    Text(text = "$key: $value", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                Text(text = "No additional data available for this item.",
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    } else {
        Text(text = "Object not found")
    }
}