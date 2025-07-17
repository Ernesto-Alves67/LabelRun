package com.scherzolambda.labelrun.ui.screens.recover
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.scherzolambda.labelrun.ui.components.AppTextField
import com.scherzolambda.labelrun.ui.components.BasicHeader
import com.scherzolambda.labelrun.ui.components.PrimaryButton

@Composable
fun RecoverPassEmailScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    onContinueClick: () -> Unit,
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
            text = "Esqueceu sua senha?\nDigite seu e-mail para que possamos enviar\num código de verificação:",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "E-mail",
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Continuar",
            onClick = onContinueClick
        )
    }
}
