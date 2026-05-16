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
import br.com.feedtheeldergods.GameViewModel

@Composable
fun PlayScreen(
    viewModel: GameViewModel,
    onDone: () -> Unit
) {
    val god = viewModel.currentGod ?: return

    val cthulhuOptions = listOf(
        "💥" to "Destroy a City",
        "👁️" to "Haunt Dreams",
        "🌊" to "Summon Storms",
        "🌀" to "Drive Cultists Mad"
    )
    val hasturOptions = listOf(
        "🎭" to "Stage a Play",
        "🤫" to "Whisper Madness",
        "👑" to "Corrupt a King",
        "🌀" to "Rewrite Reality"
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
                text = "what shall ${god.name} do for fun?",
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "choose your chaos.",
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    onClick = {
                        viewModel.play(index + 1)
                        onDone()
                    }
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}