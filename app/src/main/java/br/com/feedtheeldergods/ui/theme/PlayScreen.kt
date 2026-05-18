package br.com.feedtheeldergods.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import br.com.feedtheeldergods.view_model.GameViewModel

@Composable
fun PlayScreen(
    viewModel: GameViewModel,
    onInsanity: () -> Unit,
    onDone: () -> Unit
) {
    val god = viewModel.currentGod ?: return

    val cthulhuOptions = listOf(
        "💥" to "Destruir cidades",
        "👁️" to "Assombrar sonhos",
        "🌊" to "Invocar tempestades",
        "🌀" to "Enlouquecer"
    )
    val hasturOptions = listOf(
        "🎭" to "Encenar uma peça",
        "🤫" to "Sussurrar loucuras",
        "👑" to "Corromper um rei",
        "🌀" to "Reescrever realidade"
    )
    val options = when (god.name) {
        "Hastur" -> hasturOptions
        else     -> cthulhuOptions
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
    ) {
        StarfieldBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Como ${god.name} se divertirá hoje?",
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Escolha seu caos.",
                fontFamily = CrimsonText,
                fontSize = 14.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(36.dp))

            options.forEachIndexed { index, (icon, label) ->
                EldritchButton(
                    icon = icon,
                    label = label,
                    horizontal = true,
                    modifier = Modifier.fillMaxWidth().height(58.dp),
                    onClick = {
                        when (label) {
                            "Assombrar sonhos",
                            "Sussurrar loucuras"
                                -> onInsanity()
                            else -> {
                                viewModel.play(index + 1)
                                onDone()
                            }
                        }
                    }
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}