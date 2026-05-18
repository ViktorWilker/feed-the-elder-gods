package br.com.feedtheeldergods.ui.theme.games

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.feedtheeldergods.classes.Persona
import br.com.feedtheeldergods.ui.theme.BgCard
import br.com.feedtheeldergods.ui.theme.BgCardPressed
import br.com.feedtheeldergods.ui.theme.BgDeep
import br.com.feedtheeldergods.ui.theme.BorderAccent
import br.com.feedtheeldergods.ui.theme.BorderSubtle
import br.com.feedtheeldergods.ui.theme.Cinzel
import br.com.feedtheeldergods.ui.theme.CrimsonText
import br.com.feedtheeldergods.ui.theme.EldritchButton
import br.com.feedtheeldergods.ui.theme.StarfieldBackground
import br.com.feedtheeldergods.ui.theme.TextMuted
import br.com.feedtheeldergods.ui.theme.TextPrimary
import br.com.feedtheeldergods.ui.theme.TextSecond
import br.com.feedtheeldergods.view_model.GameViewModel
import br.com.feedtheeldergods.view_model.InsanityViewModel
import kotlinx.coroutines.delay
import kotlin.repeat

@Composable
fun InsanityMinigame(
    gameViewModel: GameViewModel,
    onDone: () -> Unit,
) {
    val context = LocalContext.current
    val viewModel: InsanityViewModel = viewModel()
    val god = gameViewModel.currentGod ?: return

    var timeLeftMs by remember { mutableStateOf(30_000L) }
    var gameOver by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.init(context)
        viewModel.onCorrect = { timeLeftMs += 15_000L }
        viewModel.startGame()
    }

    LaunchedEffect(Unit) {
        var lastTick = System.currentTimeMillis()
        while (timeLeftMs > 0) {
            delay(50)
            val now = System.currentTimeMillis()
            val delta = now - lastTick
            lastTick = now
            timeLeftMs = (timeLeftMs - delta).coerceAtLeast(0)
        }
        gameOver = true
    }

    val persona = viewModel.currentPersona
    val grid = viewModel.currentGrid

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
    ) {
        StarfieldBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(48.dp))

            Text(
                text = "despedace a mente deles",
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = TextPrimary,
                letterSpacing = 1.sp,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "escolha duas visões para corromper a presa de ${god.name}",
                fontFamily = CrimsonText,
                fontSize = 14.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val timerColor = if (timeLeftMs < 8000) Color(0xFFC4522A) else TextPrimary
                Text(
                    text = "${(timeLeftMs / 1000L) + 1}s",
                    fontFamily = Cinzel,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = timerColor,
                )
                Text(
                    text = "🧠 ${viewModel.sanityDrained}",
                    fontFamily = Cinzel,
                    fontSize = 20.sp,
                    color = TextPrimary,
                )
            }

            Spacer(Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(BorderSubtle)
            ) {
                val barColor = if (timeLeftMs < 8000) Color(0xFFC4522A) else Color(0xFF8B5AB4)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(timeLeftMs / 30_000f)
                        .background(barColor)
                )
            }

            Spacer(Modifier.height(20.dp))

            if (persona != null) {
                PersonaCard(persona = persona)

                Spacer(Modifier.height(24.dp))

                grid.chunked(3).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        row.forEach { symbol ->
                            SymbolButton(
                                symbol = symbol,
                                selected = symbol in viewModel.selectedElements,
                                modifier = Modifier.weight(1f),
                                onClick = { if (!gameOver) viewModel.selectElement(symbol) }
                            )
                        }
                        repeat(3 - row.size) {
                            Spacer(Modifier.weight(1f))
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }
        }

        if (gameOver) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xCC0D0B14)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        text = when {
                            viewModel.sanityDrained >= 60 -> "corrupção magnífica."
                            viewModel.sanityDrained >= 30 -> "suas mentes... desmoronando."
                            else -> "resistiram. decepcionante."
                        },
                        fontFamily = Cinzel,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = TextPrimary,
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.sp,
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "${viewModel.sanityDrained} de sanidade drenada",
                        fontFamily = CrimsonText,
                        fontSize = 17.sp,
                        color = TextSecond,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(Modifier.height(36.dp))
                    EldritchButton(
                        icon = "✔️",
                        label = "voltar",
                        horizontal = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        onClick = {
                            gameViewModel.feedSanity(viewModel.sanityDrained)
                            onDone()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun PersonaCard(persona: Persona) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(BgCard)
            .border(0.5.dp, BorderSubtle, RoundedCornerShape(10.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = persona.profile.name,
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = TextPrimary,
            )
            Text(
                text = "${persona.profile.age} anos",
                fontFamily = CrimsonText,
                fontSize = 14.sp,
                color = TextMuted,
            )
        }
        Text(
            text = persona.profile.occupation,
            fontFamily = CrimsonText,
            fontSize = 13.sp,
            color = TextMuted,
            fontStyle = FontStyle.Italic,
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = persona.profile.bio,
            fontFamily = CrimsonText,
            fontSize = 15.sp,
            color = TextSecond,
            lineHeight = 22.sp,
        )
    }
}

@Composable
private fun SymbolButton(
    symbol: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val bgColor by animateColorAsState(
        targetValue = if (selected) BgCardPressed else BgCard,
        animationSpec = tween(150),
        label = "sym_bg_$symbol"
    )
    val borderColor by animateColorAsState(
        targetValue = if (selected) BorderAccent else BorderSubtle,
        animationSpec = tween(150),
        label = "sym_border_$symbol"
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) TextPrimary else TextSecond,
        animationSpec = tween(150),
        label = "sym_text_$symbol"
    )

    Box(
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(0.5.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol.uppercase(),
            fontFamily = Cinzel,
            fontSize = 11.sp,
            color = textColor,
            letterSpacing = 1.sp,
            textAlign = TextAlign.Center,
        )
    }
}