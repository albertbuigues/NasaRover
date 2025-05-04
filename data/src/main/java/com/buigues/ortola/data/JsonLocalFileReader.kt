package com.buigues.ortola.data

import android.content.Context
import android.net.Uri
import com.buigues.ortola.data.models.NasaInstructions

class JsonLocalFileReader: JsonReader<NasaInstructions, Uri> {

    override suspend fun readJson(context: Context, source: Uri): NasaInstructions? {
        return null
    }
}