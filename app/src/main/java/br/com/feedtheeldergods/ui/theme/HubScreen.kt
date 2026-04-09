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
fun HubScreen(
    viewModel: GameViewModel,
    onFeed: () -> Unit,
    onPlay: () -> Unit,
    onRest: () -> Unit,
    onBathe: () -> Unit,
    onBathroom: () -> Unit
) {
    val god = viewModel.currentGod ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // status
        Text(text = god.name, fontSize = 32.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        StatusBar(label = "🍖 Hunger",    value = god.hunger)
        StatusBar(label = "😊 Happiness", value = god.happiness)
        StatusBar(label = "😴 Tiredness", value = god.tiredness)
        StatusBar(label = "🚽 Bathroom",  value = god.bathroom)
        StatusBar(label = "🧼 Dirtiness", value = god.dirtiness)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Age: ${god.age} / 50", fontSize = 14.sp)

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.lastMessage.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = viewModel.lastMessage,
                    modifier = Modifier.padding(12.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionButton(label = "🍖 Feed",  modifier = Modifier.weight(1f), onClick = onFeed)
            ActionButton(label = "🎮 Play",  modifier = Modifier.weight(1f), onClick = onPlay)
            ActionButton(label = "💤 Rest",  modifier = Modifier.weight(1f), onClick = onRest)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionButton(label = "🛁 Bathe",    modifier = Modifier.weight(1f), onClick = onBathe)
            ActionButton(label = "🚽 Bathroom", modifier = Modifier.weight(1f), onClick = onBathroom)
        }
    }
}
@Composable
fun StatusBar(label: String, value: Int) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontSize = 13.sp)
            Text(text = "$value/100", fontSize = 13.sp)
        }
        LinearProgressIndicator(
            progress = { value / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
    }
}

@Composable
fun ActionButton(label: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.height(52.dp)
    ) {
        Text(text = label, fontSize = 13.sp)
    }
}