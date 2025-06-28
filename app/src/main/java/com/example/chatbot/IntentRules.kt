
package com.example.chatbot

class IntentRules {
    private val rules = mapOf(
        "hi" to "Hello there!",
        "bye" to "Goodbye!",
        "help" to "I can answer simple yes/no questions."
    )

    fun getRuleBasedResponse(input: String): String? {
        val cleaned = input.lowercase()
        for ((key, response) in rules) {
            if (cleaned.contains(key)) return response
        }
        return null
    }
}
