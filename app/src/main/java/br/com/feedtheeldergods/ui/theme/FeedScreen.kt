package br.com.feedtheeldergods.ui.theme

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.feedtheeldergods.GameViewModel

@Composable
fun FeedScreen(
    viewModel: GameViewModel,
    onSouls: () -> Unit,
    onDone: () -> Unit
) {
    val god = viewModel.currentGod ?: return

    val options = listOf(
        "👻" to "Souls",
        "🧠" to "Sanity",
        "😱" to "Nightmares",
        "💭" to "Dreams",
        "⭐" to "Starlight",
        "🧎" to "Cultists"
    )


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
                text = "what shall ${god.name} eat?",
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp,
                color = TextPrimary,           // ← cor dourada
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "choose wisely. or don't.",
                fontFamily = CrimsonText,
                fontSize = 14.sp,
                color = TextMuted,             // ← cor acinzentada
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(36.dp))

            options.forEachIndexed{ index, (icon, label) ->
                EldritchButton(
                    icon = icon,
                    label = label,
                    horizontal = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    onClick = {
                        if(label == "Souls"){
                            onSouls()
                        } else{
                            viewModel.feed(index+ 1)
                            onDone()
                        }
                    }
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun FeedScreenPreview() {
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
                text = "what shall Cthulhu eat?",
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "choose wisely. or don't.",
                fontFamily = CrimsonText,
                fontSize = 16.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(36.dp))
            listOf("👻" to "Souls", "🧠" to "Sanity", "😱" to "Nightmares", "💭" to "Dreams", "⭐" to "Starlight", "🧎" to "Cultists")
                .forEach { (icon, label) ->
                    EldritchButton(icon = icon, label = label, modifier = Modifier.fillMaxWidth().height(52.dp), onClick = {})
                    Spacer(Modifier.height(8.dp))
                }
        }
    }
}