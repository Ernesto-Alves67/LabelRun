
package com.scherzolambda.labelrun.navigation

import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.scherzolambda.labelrun.ui.screens.AuthScreen
import com.scherzolambda.labelrun.ui.screens.recover.RecoverPassCodeScreen
import com.scherzolambda.labelrun.ui.screens.recover.RecoverPassEmailScreen
import com.scherzolambda.labelrun.ui.screens.recover.RecoverPassResetScreen


sealed class AppRoute(val route: String) {
    object Login : AppRoute("login")
    object Register : AppRoute("register")
    object Home : AppRoute("home")

    object RecoverEmail : AppRoute("recover_password/email")
    object RecoverCode : AppRoute("recover_password/code")
    object RecoverReset : AppRoute("recover_password/reset")
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var code by rememberSaveable { mutableStateOf("") }

    NavHost(navController = navController, startDestination = AppRoute.Login.route) {

        // Login Screen
        composable(AppRoute.Login.route) {
            AuthScreen(
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                onLoginClick = {
                    navController.navigate(AppRoute.Home.route)
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
//        composable(AppRoute.Register.route) {
//            RegisterScreen(
//                email = email,
//                onEmailChange = { email = it },
//                password = password,
//                onPasswordChange = { password = it },
//                confirmPassword = confirmPassword,
//                onConfirmPasswordChange = { confirmPassword = it },
//                onRegisterClick = {
//                    navController.navigate(AppRoute.Home.route)
//                },
//                onBackClick = {
//                    navController.popBackStack()
//                }
//            )
//        }

        // Home Screen TODO
//        composable(AppRoute.Home.route) {
//            HomeScreen(onLogoutClick = {
//                navController.popBackStack(AppRoute.Login.route, inclusive = false)
//            })
//        }

        // Recover Password - Email
        composable(AppRoute.RecoverEmail.route) {
            RecoverPassEmailScreen(
                email = email,
                onEmailChange = { email = it },
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
                onCodeChange = { code = it },
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
                onNewPasswordChange = { password = it },
                onConfirmPasswordChange = { confirmPassword = it },
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
