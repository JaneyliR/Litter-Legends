package com.example.litterlegends_.ui.screens

import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.litterlegends_.repository.MongoRepository
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.ExecutorService

class CameraViewModel : ViewModel() {
    var userScore by mutableStateOf(0)
        private set

    fun addPoints() {
        userScore += 20
    }

    fun saveImageToDatabase(imageUri: Uri, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                MongoRepository.uploadImage(imageUri)
                addPoints()
                onSuccess()
            } catch (e: Exception) {
                Log.e("MongoDB", "Failed to upload image: ${'$'}{e.message}")
            }
        }
    }
}

@Composable
fun CameraScreen(viewModel: CameraViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalContext.current as LifecycleOwner
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val imageCapture = remember { ImageCapture.Builder().build() }
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
    val cameraExecutor: ExecutorService = remember { java.util.concurrent.Executors.newSingleThreadExecutor() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (capturedImageUri == null) {
            Button(onClick = {
                val photoFile = File(context.cacheDir, "captured_image.jpg")
                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
                imageCapture.takePicture(outputOptions, cameraExecutor,
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            capturedImageUri = Uri.fromFile(photoFile)
                        }
                        override fun onError(exception: ImageCaptureException) {
                            Log.e("Camera", "Photo capture failed: ${'$'}{exception.message}")
                        }
                    })
            }) {
                Text("Take Picture")
            }
        } else {
            Image(
                bitmap = capturedImageUri!!.asImageBitmap(),
                contentDescription = "Captured Image",
                modifier = Modifier.size(200.dp)
            )
            Row {
                Button(onClick = { capturedImageUri = null }) {
                    Text("Retake")
                }
                Button(onClick = {
                    viewModel.saveImageToDatabase(capturedImageUri!!) {
                        capturedImageUri = null
                        showMessage()
                    }
                }) {
                    Text("Confirm")
                }
            }
        }
    }
}

@Composable
fun showMessage() {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("You are a true warrior, the earth thanks you!") },
        text = { Text(getRandomTrashFact()) },
        confirmButton = {
            Button(onClick = { /* Close Dialog */ }) {
                Text("OK")
            }
        }
    )
}

fun getRandomTrashFact(): String {
    val facts = listOf(
        "Plastic can take up to 1,000 years to decompose!",
        "Recycling one aluminum can saves enough energy to run a TV for 3 hours.",
        "Only 9% of plastic waste ever produced has been recycled."
    )
    return facts.random()
}
