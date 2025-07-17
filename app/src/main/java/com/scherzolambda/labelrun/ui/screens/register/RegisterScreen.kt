package com.scherzolambda.labelrun.ui.screens.register
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.scherzolambda.labelrun.ui.components.AppTextField
import com.scherzolambda.labelrun.ui.components.BasicHeader
import com.scherzolambda.labelrun.ui.components.PrimaryButton

@Composable
fun RegisterScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BasicHeader(title = "CADASTRAR", onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(24.dp))

        AppTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "E-mail",
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Senha",
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
            text = "Cadastrar",
            onClick = onRegisterClick
        )
    }
}