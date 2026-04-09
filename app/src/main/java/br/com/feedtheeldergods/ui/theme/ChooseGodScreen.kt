package br.com.feedtheeldergods.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChooseGodScreen(onGodSelected: (Int) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "— feed the elder gods —",
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "choose the god you wish to adopt",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { onGodSelected(1) },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text(text = "🐙  Cthulhu", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onGodSelected(2) },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            Text(text = "👑  Hastur", fontSize = 20.sp)
        }
    }
}
