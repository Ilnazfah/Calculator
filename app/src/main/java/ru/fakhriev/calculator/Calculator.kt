package ru.fakhriev.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
                .weight(1f)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(bottom = 16.dp, start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            when (val currentState = state.value) {
                is CalculatorState.Error -> {
                    Text(
                        text = currentState.expression,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                CalculatorState.Initial -> {}
                is CalculatorState.Input -> {
                    Text(
                        text = currentState.expression,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = currentState.result,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                is CalculatorState.Success -> {
                    Text(
                        text = currentState.result,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            val topSymbols = arrayOf("√", "π", "^", "!")
            topSymbols.forEach { s ->
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(s, fontSize = 32.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Clear) }
                        .weight(1f).background(MaterialTheme.colorScheme.secondary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "AC",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.PARENTHESIS)) }
                        .weight(1f).background(MaterialTheme.colorScheme.tertiary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "( )",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.PERCENT)) }
                        .weight(1f).background(MaterialTheme.colorScheme.tertiary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "%",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIVIDE)) }
                        .weight(1f).background(MaterialTheme.colorScheme.tertiary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "÷",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_7)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "7",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_8)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "8",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_9)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "9",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.MULTIPLY)) }
                        .weight(1f).background(MaterialTheme.colorScheme.tertiary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "X",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_4)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "4",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_5)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "5",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_6)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "6",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.SUBTRACT)) }
                        .weight(1f).background(MaterialTheme.colorScheme.tertiary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "-",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_1)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "1",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_2)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "2",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_3)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "3",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.ADD)) }
                        .weight(1f).background(MaterialTheme.colorScheme.tertiary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "+",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_0)) }
                        .weight(2f).background(MaterialTheme.colorScheme.primary).aspectRatio(2f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "0",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Input(Symbol.DOT)) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = ",",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier.clip(CircleShape)
                        .clickable { viewModel.processCommand(CalculatorCommand.Evaluate) }
                        .weight(1f).background(MaterialTheme.colorScheme.primary).aspectRatio(1f),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "=",
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}