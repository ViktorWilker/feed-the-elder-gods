package br.com.feedtheeldergods.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameOverScreen(
    message: String,
    onRestart: () -> Unit
) {
    val isVictory = message.contains("venceu")

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
                text = if (isVictory) "✨ as estrelas estão alinhadas ✨" else "💀 o vazio consome tudo 💀",
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
                lineHeight = 38.sp,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = if (isVictory) "você agradou os antigos." else "a escuridão não foi suficiente.",
                fontFamily = CrimsonText,
                fontSize = 14.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(36.dp))

            // caixa da mensagem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(BgCard)
                    .border(0.5.dp, BorderSubtle, RoundedCornerShape(10.dp))
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Text(
                    text = message,
                    fontFamily = CrimsonText,
                    fontSize = 17.sp,
                    color = TextSecond,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    lineHeight = 26.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(48.dp))

            EldritchButton(
                icon = if (isVictory) "✨" else "🔁",
                label = if (isVictory) "adotar outro deus" else "tentar novamente",
                horizontal = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                onClick = onRestart
            )
        }
    }
}