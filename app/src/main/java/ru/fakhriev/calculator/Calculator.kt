package ru.fakhriev.calculator

import androidx.compose.foundation.background
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

@Composable
fun Calculator(
    modifier: Modifier = Modifier
) {
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
                text = "45x8",
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "360",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            val symbols = arrayOf("√", "π", "^", "!")
            symbols.forEach { s ->
                Button(
                    onClick = {},
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
            val primary = MaterialTheme.colorScheme.primary
            val secondary = MaterialTheme.colorScheme.secondary
            val tertiary = MaterialTheme.colorScheme.tertiary
            val first =
                arrayOf("AC" to secondary, "( )" to tertiary, "%" to tertiary, "÷" to tertiary)
            val second = arrayOf("7" to primary, "8" to primary, "9" to primary, "X" to tertiary)
            val third = arrayOf("4" to primary, "5" to primary, "6" to primary, "-" to tertiary)
            val fourth = arrayOf("1" to primary, "2" to primary, "3" to primary, "+" to tertiary)
            val fifth = arrayOf("0" to primary, "," to primary, "=" to tertiary)
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
                                .weight(weight)
                                .clip(CircleShape)
                                .background(color)
                                .aspectRatio(weight),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                fontSize = 26.sp, color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }

            }
        }
    }
}