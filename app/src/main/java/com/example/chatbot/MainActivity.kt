
package com.example.chatbot

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var chatHistory: TextView
    private lateinit var inputField: EditText
    private lateinit var sendButton: Button
    private lateinit var chatbot: ChatBotEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chatHistory = findViewById(R.id.chatHistory)
        inputField = findViewById(R.id.inputField)
        sendButton = findViewById(R.id.sendButton)

        chatbot = ChatBotEngine(this)

        sendButton.setOnClickListener {
            val userInput = inputField.text.toString()
            if (userInput.isNotEmpty()) {
                appendMessage("You: $userInput")
                val botResponse = chatbot.getResponse(userInput)
                appendMessage("Bot: $botResponse")
                inputField.text.clear()
            }
        }
    }

    private fun appendMessage(message: String) {
        chatHistory.append("$message\n")
    }
}
