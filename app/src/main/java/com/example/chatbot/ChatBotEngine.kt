
package com.example.chatbot

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class ChatBotEngine(private val context: Context) {

    private val fallback = IntentRules()
    private var interpreter: Interpreter? = null

    init {
        loadModel()
    }

    private fun loadModel() {
        try {
            val assetFileDescriptor = context.assets.openFd("bert.tflite")
            val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
            val fileChannel = fileInputStream.channel
            val startOffset = assetFileDescriptor.startOffset
            val declaredLength = assetFileDescriptor.declaredLength
            val model = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
            interpreter = Interpreter(model)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getResponse(userMessage: String): String {
        val fallbackAnswer = fallback.getRuleBasedResponse(userMessage)
        if (fallbackAnswer != null) return fallbackAnswer

        return classifyWithBERT(userMessage)
    }

    private fun classifyWithBERT(query: String): String {
        if (interpreter == null) return "Sorry, no ML model loaded."

        val inputTensor = convertTextToInput(query)
        val output = Array(1) { FloatArray(2) }
        interpreter?.run(inputTensor, output)

        val confidence = output[0][0]
        return if (confidence > 0.5) "I think yes (confidence ${"%.2f".format(confidence)})"
        else "I think no (confidence ${"%.2f".format(confidence)})"
    }

    private fun convertTextToInput(text: String): ByteBuffer {
        val buffer = ByteBuffer.allocateDirect(512)
        buffer.order(ByteOrder.nativeOrder())
        text.take(128).forEach {
            buffer.put(it.code.toByte())
        }
        return buffer
    }
}
