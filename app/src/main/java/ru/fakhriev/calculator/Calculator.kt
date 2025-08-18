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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val viewModel = CalculatorViewModel()

@Composable
fun Calculator(
    modifier: Modifier = Modifier
) {
    val state = viewModel.state.value
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
            Text(
                text = state.expression,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = state.result,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            val symbols = arrayOf(Symbol.SQRT, Symbol.PI, Symbol.POWER, Symbol.FACTORIAL)
            symbols.forEach { s ->
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(s.value, fontSize = 32.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val primary = MaterialTheme.colorScheme.primary
            val secondary = MaterialTheme.colorScheme.secondary
            val tertiary = MaterialTheme.colorScheme.tertiary
            val first =
                arrayOf(Symbol.AC to secondary, Symbol.PARENTHESIS to tertiary, Symbol.PERCENT to tertiary, Symbol.DIVIDE to tertiary)
            val second = arrayOf(Symbol.DIGIT_7 to primary, Symbol.DIGIT_8 to primary, Symbol.DIGIT_9 to primary, Symbol.MULTIPLY to tertiary)
            val third = arrayOf(Symbol.DIGIT_4 to primary, Symbol.DIGIT_5 to primary, Symbol.DIGIT_6 to primary, Symbol.SUBTRACT to tertiary)
            val fourth = arrayOf(Symbol.DIGIT_1 to primary, Symbol.DIGIT_2 to primary, Symbol.DIGIT_3 to primary, Symbol.ADD to tertiary)
            val fifth = arrayOf(Symbol.DIGIT_0 to primary, Symbol.DOT to primary, Symbol.EQUALS to tertiary)
            val columnLabels = arrayOf(first, second, third, fourth, fifth)
            columnLabels.forEachIndexed { indexParent, columns ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    columns.forEachIndexed { index, pair ->
                        val label = pair.first
                        val color = pair.second
                        val weight = if (indexParent != 4) {
                            1f
                        } else {
                            if (index != 0) 1f else 2f
                        }
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    viewModel.processCommand(CalculatorCommand.Input(pair.first))
                                }
                                .weight(weight)
                                .background(color)
                                .aspectRatio(weight),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label.value,
                                fontSize = 26.sp, color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }

            }
        }
    }
}