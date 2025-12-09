package com.scherzolambda.labelrun.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scherzolambda.labelrun.ui.screens.AuthScreen
import com.scherzolambda.labelrun.ui.screens.HomeScreen
import com.scherzolambda.labelrun.ui.screens.recover.RecoverPassCodeScreen
import com.scherzolambda.labelrun.ui.screens.recover.RecoverPassEmailScreen
import com.scherzolambda.labelrun.ui.screens.recover.RecoverPassResetScreen
import com.scherzolambda.labelrun.ui.screens.register.RegisterScreen
import com.scherzolambda.labelrun.ui.viewmodel.AuthViewModel
import com.scherzolambda.labelrun.ui.viewmodel.LoginState


sealed class AppRoute(val route: String) {
    object Login : AppRoute("login")
    object Register : AppRoute("register")
    object Home : AppRoute("home")

    object RecoverEmail : AppRoute("recover_password/email")
    object RecoverCode : AppRoute("recover_password/code")
    object RecoverReset : AppRoute("recover_password/reset")
}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val loginState by authViewModel.loginState.collectAsState()

    // Coleta valores do ViewModel
    val email by authViewModel.email.collectAsState()
    val password by authViewModel.password.collectAsState()
    val confirmPassword by authViewModel.confirmPassword.collectAsState()
    val code by authViewModel.code.collectAsState()

    NavHost(navController = navController, startDestination = AppRoute.Login.route) {

        // Login Screen
        composable(AppRoute.Login.route) {
            LaunchedEffect(loginState) {
                when (val state = loginState) {
                    is LoginState.Success -> {
                        Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppRoute.Home.route) {
                            popUpTo(AppRoute.Login.route) { inclusive = true }
                        }
                        authViewModel.resetLoginState()
                    }
                    is LoginState.Error -> {
                        Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                        authViewModel.resetLoginState()
                    }
                    else -> { /* Idle ou Loading */ }
                }
            }

            AuthScreen(
                email = email,
                onEmailChange = { authViewModel.setEmail(it) },
                password = password,
                onPasswordChange = { authViewModel.setPassword(it) },
                onLoginClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        authViewModel.login(email, password)
                    } else {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                    }
                },
                onForgotPasswordClick = {
                    navController.navigate(AppRoute.RecoverEmail.route)
                },
                onRegisterClick = {
                    navController.navigate(AppRoute.Register.route)
                },
                onGoogleLoginClick = {
                    // TODO: Implement Google Login
                }
            )
        }

        // Register Screen TODO
        composable(AppRoute.Register.route) {
            RegisterScreen(
                email = email,
                onEmailChange = { authViewModel.setEmail(it) },
                password = password,
                onPasswordChange = { authViewModel.setPassword(it) },
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = { authViewModel.setConfirmPassword(it) },
                onRegisterClick = {
                    // Use os valores do ViewModel para registrar
                    authViewModel.register(email, password, "")
                    navController.navigate(AppRoute.Home.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Home Screen TODO
        composable(AppRoute.Home.route) {
            HomeScreen(onLogoutClick = {
                authViewModel.logout()
                navController.popBackStack(AppRoute.Login.route, inclusive = false)
            })
        }

        // Recover Password - Email
        composable(AppRoute.RecoverEmail.route) {
            RecoverPassEmailScreen(
                email = email,
                onEmailChange = { authViewModel.setEmail(it) },
                onContinueClick = {
                    navController.navigate(AppRoute.RecoverCode.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Recover Password - Code
        composable(AppRoute.RecoverCode.route) {
            RecoverPassCodeScreen(
                code = code,
                onCodeChange = { authViewModel.setCode(it) },
                onConfirmClick = {
                    navController.navigate(AppRoute.RecoverReset.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Recover Password - Reset
        composable(AppRoute.RecoverReset.route) {
            RecoverPassResetScreen(
                newPassword = password,
                confirmPassword = confirmPassword,
                onNewPasswordChange = { authViewModel.setPassword(it) },
                onConfirmPasswordChange = { authViewModel.setConfirmPassword(it) },
                onConfirmClick = {
                    navController.navigate(AppRoute.Login.route) {
                        popUpTo(AppRoute.Login.route) { inclusive = true }
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
