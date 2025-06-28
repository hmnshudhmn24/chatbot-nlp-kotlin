
# 🤖 Kotlin Chatbot with NLP (TFLite)

## 📄 Project Description

A Kotlin chatbot app demonstrating hybrid NLP with rule-based responses plus a BERT question-answering model through TensorFlow Lite. Users can deploy on Android or JVM, and extend with richer models later. A great starter for Kotlin conversational AI.

---

## 🚀 Features

- Rule-based fallback
- On-device Q&A with BERT
- Android-ready UI
- JVM-deployable Kotlin code

---

## ⚙️ How to Run

1. **Clone the repo**
   ```bash
   git clone https://github.com/yourusername/kotlin-chatbot-nlp-kotlin.git
   ```
2. **Open in Android Studio**
   - File > Open > select the project
   - Let Gradle sync

3. **Configure**
   - Place your `bert.tflite` in `app/src/main/assets/`
   - Adjust rule intents in `IntentRules.kt`

4. **Build & Run**
   - Connect a phone or use emulator
   - Click **Run**

---

## 🧩 Customization

- Swap in your own `bert.tflite`
- Enhance with a bigger vocabulary or new rules
- Integrate Jetpack Compose for advanced UI

---
