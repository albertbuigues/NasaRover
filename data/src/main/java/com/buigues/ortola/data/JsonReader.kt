package com.buigues.ortola.data

import android.content.Context

interface JsonReader<T,S> {
    suspend fun readJson(context: Context, source: S): T?
}