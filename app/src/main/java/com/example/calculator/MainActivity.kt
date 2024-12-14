package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var resultText by remember { mutableStateOf("0") }
    var currentInput by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }
    var firstOperand by remember { mutableStateOf("") }
    var expression by remember { mutableStateOf("") }
    var isResultDisplayed by remember { mutableStateOf(false) }

    fun clearAll() {
        resultText = "0"
        currentInput = ""
        operation = ""
        firstOperand = ""
        expression = ""
        isResultDisplayed = false
    }

    fun calculateResult() {
        if (firstOperand.isNotEmpty() && currentInput.isNotEmpty() && operation.isNotEmpty()) {
            val num1 = firstOperand.toDoubleOrNull()
            val num2 = currentInput.toDoubleOrNull()
            val result = when (operation) {
                "+" -> num1?.plus(num2 ?: 0.0)
                "-" -> num1?.minus(num2 ?: 0.0)
                "*" -> num1?.times(num2 ?: 0.0)
                "/" -> if (num2 != 0.0) num1?.div(num2 ?: 0.0) else null
                else -> null
            }
            expression = "$firstOperand $operation $currentInput ="
            resultText = result?.toString() ?: "Error"
            currentInput = ""
            operation = ""
            firstOperand = ""
            isResultDisplayed = true
        }
    }

    fun handleOperation(op: String) {
        if (isResultDisplayed) {
            firstOperand = resultText
            isResultDisplayed = false
        } else if (currentInput.isNotEmpty()) {
            firstOperand = currentInput
        }
        operation = op
        expression = "$firstOperand $operation"
        currentInput = ""
        resultText = ""
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Display area
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = expression,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            Text(
                text = resultText,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }

        // Buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val buttons = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("C", "0", "=", "+")
            )

            buttons.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    row.forEach { label ->
                        CalculatorButton(
                            label = label,
                            onClick = {
                                when (label) {
                                    "C" -> clearAll()
                                    "=" -> calculateResult()
                                    "+", "-", "*", "/" -> handleOperation(label)
                                    else -> {
                                        if (isResultDisplayed) {
                                            clearAll()
                                        }
                                        currentInput += label
                                        resultText = currentInput
                                        expression = if (operation.isNotEmpty()) {
                                            "$firstOperand $operation $currentInput"
                                        } else {
                                            currentInput
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit, size: Dp = 80.dp) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorAppPreview() {
    CalculatorTheme {
        CalculatorApp()
    }
}
