package br.com.feedtheeldergods.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val BgDeep        = Color(0xFF12101A)
val BgCard        = Color(0xFF1E1A2E)
val BgCardPressed = Color(0xFF28143C)
val BorderSubtle  = Color(0xFF2E2445)
val BorderAccent  = Color(0xFF4A3570)

val TextPrimary   = Color(0xFFE8D5A3)
val TextSecond    = Color(0xFFB0A0C0)
val TextMuted     = Color(0xFF7A6A8A)

val GradHunger    = listOf(Color(0xFF6A2A1A), Color(0xFFC4522A))
val GradHappy     = listOf(Color(0xFF1A4A2A), Color(0xFF3A9A5A))
val GradTired     = listOf(Color(0xFF1A2A4A), Color(0xFF2A5A9A))
val GradBathroom  = listOf(Color(0xFF4A3A1A), Color(0xFF9A7A2A))
val GradDirt      = listOf(Color(0xFF2A3A1A), Color(0xFF5A7A2A))


val Cinzel = FontFamily(
    Font(br.com.feedtheeldergods.R.font.cinzel_regular,  FontWeight.Normal),
    Font(br.com.feedtheeldergods.R.font.cinzel_semibold, FontWeight.SemiBold),
)
val CrimsonText = FontFamily(
    Font(br.com.feedtheeldergods.R.font.crimsontext_regular, FontWeight.Normal),
)