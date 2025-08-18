package ru.fakhriev.calculator

import androidx.compose.runtime.mutableStateOf

class CalculatorViewModel {
    val state = mutableStateOf(
        Display(
            expression = "45x8",
            result = "360"
        )
    )

    fun processCommand(command: CalculatorCommand) {
        when(command) {
            CalculatorCommand.Clear -> {

            }
            CalculatorCommand.Evaluate -> {

            }
            is CalculatorCommand.Input -> {
                when(command.symbol) {
                    Symbol.DIGIT_0 -> {}
                    Symbol.DIGIT_1 -> {}
                    Symbol.DIGIT_2 -> {}
                    Symbol.DIGIT_3 -> {}
                    Symbol.DIGIT_4 -> {}
                    Symbol.DIGIT_5 -> {}
                    Symbol.DIGIT_6 -> {}
                    Symbol.DIGIT_7 -> {}
                    Symbol.DIGIT_8 -> {}
                    Symbol.DIGIT_9 -> {}
                    Symbol.ADD -> {}
                    Symbol.SUBTRACT -> {}
                    Symbol.MULTIPLY -> {}
                    Symbol.DIVIDE -> {}
                    Symbol.PERCENT -> {}
                    Symbol.POWER -> {}
                    Symbol.FACTORIAL -> {}
                    Symbol.SQRT -> {}
                    Symbol.PI -> {}
                    Symbol.DOT -> {}
                    Symbol.PARENTHESIS -> {}
                    Symbol.AC -> { state.value = Display("", "") }
                    Symbol.EQUALS -> {}
                }
            }
        }
    }
}

sealed interface CalculatorCommand {

    data object Clear : CalculatorCommand
    data object Evaluate : CalculatorCommand
    data class Input(val symbol: Symbol) : CalculatorCommand
}

enum class Symbol(val value: String) {
    DIGIT_0("0"),
    DIGIT_1("1"),
    DIGIT_2("2"),
    DIGIT_3("3"),
    DIGIT_4("4"),
    DIGIT_5("5"),
    DIGIT_6("6"),
    DIGIT_7("7"),
    DIGIT_8("8"),
    DIGIT_9("9"),
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("X"),
    DIVIDE("/"),
    PERCENT("%"),
    POWER("^"),
    FACTORIAL("!"),
    SQRT(""),
    PI("^"),
    DOT(","),
    PARENTHESIS("( )"),
    AC("AC"),
    EQUALS("=")
}

data class Display(
    val expression: String,
    val result: String
)