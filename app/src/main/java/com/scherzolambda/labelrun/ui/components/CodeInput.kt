package com.scherzolambda.labelrun.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodeInput(
    code: String,
    onCodeChange: (String) -> Unit,
    length: Int = 4
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(length) { index ->
            OutlinedTextField(
                value = code.getOrNull(index)?.toString() ?: "",
                onValueChange = { newChar ->
                    val updated = code.toMutableList().apply {
                        if (newChar.isNotEmpty()) this[index] = newChar.first()
                    }.joinToString("")
                    onCodeChange(updated)
                },
                singleLine = true,
                modifier = Modifier
                    .width(56.dp)
                    .height(56.dp),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = VisualTransformation.None
            )
        }
    }
}
