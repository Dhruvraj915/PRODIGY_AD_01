package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {

    private lateinit var inputText: TextView
    private lateinit var resultText: TextView
    private var expression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText = findViewById(R.id.inputText)
        resultText = findViewById(R.id.resultText)

        val buttons = listOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2", R.id.btn3 to "3",
            R.id.btn4 to "4", R.id.btn5 to "5", R.id.btn6 to "6", R.id.btn7 to "7",
            R.id.btn8 to "8", R.id.btn9 to "9", R.id.btnDot to ".",
            R.id.btnAdd to "+", R.id.btnMinus to "-", R.id.btnMultiply to "*",
            R.id.btnDivide to "/"
        )

        for ((id, value) in buttons) {
            findViewById<Button>(id).setOnClickListener {
                expression += value
                inputText.text = expression
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            expression = ""
            inputText.text = ""
            resultText.text = ""
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            if (expression.isNotEmpty()) {
                expression = expression.dropLast(1)
                inputText.text = expression
            }
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            try {
                val engine = ScriptEngineManager().getEngineByName("rhino")
                val result = engine.eval(expression)
                resultText.text = "= $result"
            } catch (e: Exception) {
                resultText.text = "Error"
            }
        }
    }
}
