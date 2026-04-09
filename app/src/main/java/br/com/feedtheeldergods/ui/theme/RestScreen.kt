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
fun RestScreen(
    viewModel: GameViewModel,
    onDone: () -> Unit
) {
    val god = viewModel.currentGod ?: return
    var hours by remember { mutableStateOf(4f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "how long shall ${god.name} rest?",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "${hours.toInt()} hours",
            fontSize = 48.sp,
            textAlign = TextAlign.Center
        )

        if (hours.toInt() >= 8) {
            Text(
                text = "fully rested!",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = hours,
            onValueChange = { hours = it },
            valueRange = 1f..8f,
            steps = 6,  // 1,2,3,4,5,6,7,8 = 6 passos entre eles
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "1h", fontSize = 12.sp)
            Text(text = "8h", fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.rest(hours.toInt())
                onDone()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "rest now", fontSize = 18.sp)
        }
    }
}
