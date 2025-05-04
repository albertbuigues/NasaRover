package com.buigues.ortola.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.buigues.ortola.data.models.NasaInstructions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.FileNotFoundException

class JsonLocalFileReader(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): JsonReader<NasaInstructions, Uri> {

    override suspend fun readJson(context: Context, source: Uri): NasaInstructions? {
        var jsonString: String? = null
        var result: NasaInstructions? = null
        withContext(dispatcher) {
            try {
                jsonString = context.contentResolver.openInputStream(source)?.use { input ->
                    input.bufferedReader().use { it.readText() }
                }
            } catch (_: FileNotFoundException) {
                Toast.makeText(context, "File not found", Toast.LENGTH_SHORT).show()
            }
            jsonString?.let { json ->
                try {
                    result = Json.decodeFromString<NasaInstructions>(json)
                } catch (_: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Invalid Instructions", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return result
    }
}