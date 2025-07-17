package com.scherzolambda.labelrun.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scherzolambda.labelrun.ui.components.AppTextField
import com.scherzolambda.labelrun.ui.components.ClickableTextLink
import com.scherzolambda.labelrun.ui.components.PrimaryButton
import com.scherzolambda.labelrun.ui.theme.LabelRunTheme

@Composable
fun AuthScreen(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onGoogleLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Logo
//        Image(
//            painter = painterResource(id = R.drawable.ic_logo), // coloque seu logo
//            contentDescription = "Logo",
//            modifier = Modifier
//                .size(120.dp)
//        )

        Spacer(modifier = Modifier.height(32.dp))

        // E-mail
        AppTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "E-mail",
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Senha
        AppTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Senha",
            leadingIcon = Icons.Default.Lock,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Link: Esqueci minha senha
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            ClickableTextLink(text = "Esqueci minha senha", onClick = onForgotPasswordClick)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Login
        PrimaryButton(
            text = "Entrar",
            onClick = onLoginClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Link: Não tem cadastro?
        Row {
            Text("Não tem cadastro? ")
            ClickableTextLink(text = "Clique aqui!", onClick = onRegisterClick)
        }

        Spacer(modifier = Modifier.height(24.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun AuthScreenPreview(){
    LabelRunTheme {
        AuthScreen(
            email = "",
            password = "",
            onEmailChange = {},
            onLoginClick = {},
            onRegisterClick = {},
            onPasswordChange = {},
            onGoogleLoginClick = {},
            onForgotPasswordClick = {}
        )
    }
}