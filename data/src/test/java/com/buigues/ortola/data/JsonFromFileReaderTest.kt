package com.buigues.ortola.data

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.buigues.ortola.data.models.Coordinates
import com.buigues.ortola.data.models.NasaInstructions
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.ByteArrayInputStream

@RunWith(JUnit4::class)
class JsonFromFileReaderTest {

    val context = mockk<Context>()
    val uri = mockk<Uri>()
    val contentResolver = mockk<ContentResolver>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { context.contentResolver } returns contentResolver
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when valid json then return instance of NasaInstructions`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val reader = JsonLocalFileReader(testDispatcher)
        val jsonString = """
            {
                "topRightCorner": {
                    "x": 4,
                    "y": 4
                },
                "roverPosition": {
                    "x": 0,
                    "y": 0
                },
                "roverDirection": "N",
                "movements": "RML"
            }
        """.trimIndent()
        val expectedModel = NasaInstructions(
            topRightCorner = Coordinates(4,4),
            roverPosition = Coordinates(0,0),
            roverDirection = "N",
            movements = "RML"
        )
        every { contentResolver.openInputStream(any()) } returns ByteArrayInputStream(jsonString.toByteArray())
        val result = reader.readJson(context, uri)
        assertEquals(expectedModel, result)
    }
}