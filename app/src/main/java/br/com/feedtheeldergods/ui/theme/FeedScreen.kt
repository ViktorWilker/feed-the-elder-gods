package br.com.feedtheeldergods.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.feedtheeldergods.GameViewModel

@Composable
fun FeedScreen(
    viewModel: GameViewModel,
    onDone: () -> Unit
) {
    val god = viewModel.currentGod ?: return

    val options = listOf(
        "👻 Souls",
        "🧠 Sanity",
        "😱 Nightmares",
        "💭 Dreams",
        "⭐ Starlight",
        "🧎 Cultists"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "what shall ${god.name} eat?",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        options.forEachIndexed { index, label ->
            Button(
                onClick = {
                    viewModel.feed(index + 1)
                    onDone()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(52.dp)
            ) {
                Text(text = label, fontSize = 16.sp)
            }
        }
    }
}
