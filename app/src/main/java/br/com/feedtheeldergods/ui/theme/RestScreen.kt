package br.com.feedtheeldergods.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.feedtheeldergods.view_model.GameViewModel

@Composable
fun RestScreen(
    viewModel: GameViewModel,
    onDone: () -> Unit
) {
    val god = viewModel.currentGod ?: return
    var hours by remember { mutableStateOf(4f) }
    val isFullyRested = hours.toInt() >= 8

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
                text = "Por quanto tempo ${god.name} vai descansar?",
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Até deuses precisam dormir.",
                fontFamily = CrimsonText,
                fontSize = 14.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(40.dp))

            // contador de horas
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(BgCard)
                    .border(0.5.dp, BorderSubtle, RoundedCornerShape(12.dp))
                    .padding(horizontal = 40.dp, vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${hours.toInt()}",
                        fontFamily = Cinzel,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 64.sp,
                        color = TextPrimary,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = if (hours.toInt() == 1) "hora" else "horas",
                        fontFamily = CrimsonText,
                        fontSize = 18.sp,
                        color = TextMuted,
                        textAlign = TextAlign.Center,
                    )
                    if (isFullyRested) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "✨ totalmente descansado",
                            fontFamily = CrimsonText,
                            fontSize = 14.sp,
                            color = TextSecond,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            Slider(
                value = hours,
                onValueChange = { hours = it },
                valueRange = 1f..8f,
                steps = 6,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = TextPrimary,
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "1h", fontFamily = CrimsonText, fontSize = 13.sp, color = TextMuted)
                Text(text = "8h", fontFamily = CrimsonText, fontSize = 13.sp, color = TextMuted)
            }

            Spacer(Modifier.height(36.dp))

            EldritchButton(
                icon = "💤",
                label = "descansar",
                horizontal = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                onClick = {
                    viewModel.rest(hours.toInt())
                    onDone()
                }
            )
        }
    }
}