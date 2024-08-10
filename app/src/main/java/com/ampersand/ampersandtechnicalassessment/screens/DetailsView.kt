package com.ampersand.ampersandtechnicalassessment.screens

import android.util.Log
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

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (objectData != null) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Text(text = objectData.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "ID: ${objectData.id}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                when {
                    objectData.data == null -> {
                        Text("No data available for this item.", style = MaterialTheme.typography.bodyMedium)
                    }
                    objectData.data.isEmpty() -> {
                        Text("No additional data available for this item.", style = MaterialTheme.typography.bodyMedium)
                    }
                    else -> {
                        val formattedData = viewModel.getFormattedData(objectData)
                        formattedData.forEach { (key, value) ->
                            Text(text = "$key: $value", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        } else {
            Text("Select an item to view details", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

// Add this function outside the DetailsView composable
fun logError(message: String, error: Throwable) {
    Log.e("DetailsView", message, error)
}