package com.scherzolambda.labelrun.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)

val Pink10 = Color(0xFFd882ac)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)



/**
 * Agrega todas as estruturas de cores customizadas em um único objeto de tema.
 *
 * Esta classe é a que é fornecida para a UI através de `CompositionLocal`,
 * oferecendo acesso a todas as paletas de forma organizada via `AppTheme.colors`.
 */
data class AppColors(
    val content: ColorScheme,
)

/**
 * Instância pré-configurada de [AppColors] para o tema claro.
 */
val LightAppColors = AppColors(
    content = lightColorScheme()
)

/**
 * Instância pré-configurada de [AppColors] para o tema escuro.
 */
val DarkAppColors = AppColors(
    content = darkColorScheme()
)

/**
 * Provedor `CompositionLocal` interno para o sistema de cores.
 *
 * É o mecanismo que permite que o `ApplicationTheme` injete a instância correta
 * de [AppColors] (clara ou escura) na árvore de componentes, tornando-a acessível
 * via `AppTheme.colors`.
 */
internal val LocalAppColors = staticCompositionLocalOf { LightAppColors }