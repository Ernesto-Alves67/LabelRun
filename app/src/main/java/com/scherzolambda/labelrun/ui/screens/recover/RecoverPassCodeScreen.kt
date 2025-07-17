package com.scherzolambda.labelrun.ui.screens.recover


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scherzolambda.labelrun.ui.components.BasicHeader
import com.scherzolambda.labelrun.ui.components.CodeInput
import com.scherzolambda.labelrun.ui.components.PrimaryButton

@Composable
fun RecoverPassCodeScreen(
    code: String,
    onCodeChange: (String) -> Unit,
    onConfirmClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        BasicHeader(
            title = "RECUPERAR SENHA",
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Insira o código\nPara cadastrar sua nova senha, insira o token\nque você recebeu por SMS:",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        CodeInput(
            code = code.padEnd(4, ' '), // garante 4 dígitos
            onCodeChange = { onCodeChange(it.take(4)) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Confirmar",
            onClick = onConfirmClick
        )
    }
}
