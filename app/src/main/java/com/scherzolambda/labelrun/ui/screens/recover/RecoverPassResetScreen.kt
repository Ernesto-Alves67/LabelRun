package com.scherzolambda.labelrun.ui.screens.recover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scherzolambda.labelrun.ui.components.AppTextField
import com.scherzolambda.labelrun.ui.components.BasicHeader
import com.scherzolambda.labelrun.ui.components.PrimaryButton

@Composable
fun RecoverPassResetScreen(
    newPassword: String,
    confirmPassword: String,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
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
            text = "Nova senha\nCadastre sua nova senha usando\nnúmeros e caracteres:",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        AppTextField(
            value = newPassword,
            onValueChange = onNewPasswordChange,
            label = "Nova senha",
            leadingIcon = Icons.Default.Lock,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirmar senha",
            leadingIcon = Icons.Default.Lock,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Confirmar",
            onClick = onConfirmClick
        )
    }
}
