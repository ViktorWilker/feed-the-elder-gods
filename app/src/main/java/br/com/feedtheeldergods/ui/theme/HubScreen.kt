package br.com.feedtheeldergods.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.feedtheeldergods.view_model.GameViewModel
import kotlin.math.sin

//  HubScreen

@Composable
fun HubScreen(
    viewModel: GameViewModel,
    onFeed: () -> Unit,
    onPlay: () -> Unit,
    onRest: () -> Unit,
    onBathe: () -> Unit,
    onBathroom: () -> Unit,
) {
    val god = viewModel.currentGod ?: return

    val epitaph = remember(god.name) {
        when (god.name) {
            "Cthulhu" -> "Sonhador das Profundezas"
            "Hastur"  -> "O Rei de Amarelo"
            else      -> "Elder God"
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        StarfieldBackground()


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 20.dp, bottom = 160.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // nome
            Text(
                text = god.name.uppercase(),
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp,
                letterSpacing = 5.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(4.dp))

            // epitáfio
            Text(
                text = epitaph,
                fontFamily = CrimsonText,
                fontSize = 16.sp,
                color = TextMuted,
                fontStyle = FontStyle.Italic,
                letterSpacing = 1.sp,
            )

            Spacer(Modifier.height(20.dp))

            // age
            AgeBar(age = god.age, maxAge = 50)

            Spacer(Modifier.height(24.dp))

            // stats
            EldritchStatBar(label = "Fome",    value = god.hunger,    gradient = GradHunger,   icon = "🍖", dangerWhenHigh = true)
            EldritchStatBar(label = "Felicidade", value = god.happiness, gradient = GradHappy,    icon = "😊", dangerWhenHigh = false)
            EldritchStatBar(label = "Cansaço", value = god.tiredness, gradient = GradTired,    icon = "😴", dangerWhenHigh = true)
            EldritchStatBar(label = "Banheiro",  value = god.bathroom,  gradient = GradBathroom, icon = "🚽", dangerWhenHigh = true)
            EldritchStatBar(label = "Sujeira", value = god.dirtiness, gradient = GradDirt,     icon = "🧼", dangerWhenHigh = true)

            if (viewModel.lastMessage.isNotEmpty()) {
                Spacer(Modifier.height(16.dp))
                EldritchMessageBox(message = viewModel.lastMessage)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp)
                .padding(bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EldritchButton(icon = "🍖", label = "Alimentar",  modifier = Modifier.weight(1f), onClick = onFeed)
                EldritchButton(icon = "🎮", label = "Jogar",  modifier = Modifier.weight(1f), onClick = onPlay)
                EldritchButton(icon = "💤", label = "Descansar",  modifier = Modifier.weight(1f), onClick = onRest)
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EldritchButton(icon = "🛁", label = "Banho",    modifier = Modifier.weight(1f), onClick = onBathe)
                EldritchButton(icon = "🚽", label = "Banheiro", modifier = Modifier.weight(1f), onClick = onBathroom)
            }
        }
    }
}

//StarfieldBackground
@Composable
fun StarfieldBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "stars")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 60_000, easing = LinearEasing)
        ),
        label = "starTime"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind { drawStarfield(time) }
    )
}

private fun DrawScope.drawStarfield(time: Float) {
    val w = size.width
    val h = size.height
    for (i in 0 until 60) {
        val x = (sin(i * 127.1f + time * 0.003f) * 0.5f + 0.5f) * w
        val y = (sin(i * 311.7f + time * 0.002f) * 0.5f + 0.5f) * h
        val r = 1.2f + sin(i + time * 0.01f).coerceIn(-1f, 1f) * 0.5f
        val alpha = (0.35f + sin(i * 2.3f + time * 0.015f) * 0.2f).coerceIn(0.1f, 0.65f)
        drawCircle(
            color = Color(
                red   = ((120 + i % 60) / 255f),
                green = ((80  + i % 40) / 255f),
                blue  = ((180 + i % 60) / 255f),
                alpha = alpha
            ),
            radius = r,
            center = Offset(x, y)
        )
    }
}

// AgeBar
@Composable
fun AgeBar(age: Int, maxAge: Int) {
    val progress = age / maxAge.toFloat()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(BgCard)
            .border(0.5.dp, BorderSubtle, RoundedCornerShape(6.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "IDADE",
            fontFamily = Cinzel,
            fontSize = 13.sp,
            color = TextMuted,
            letterSpacing = 2.sp,
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Color(0xFF1A1025))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .background(Brush.horizontalGradient(listOf(Color(0xFF4A2A6A), Color(0xFF8B5AB4))))
            )
        }

        Text(
            text = "$age / $maxAge",
            fontFamily = Cinzel,
            fontSize = 13.sp,
            color = TextMuted,
        )
    }
}

// EldritchStatBar
@Composable
fun EldritchStatBar(
    label: String,
    value: Int,
    gradient: List<Color>,
    icon: String,
    dangerWhenHigh: Boolean = true,
) {
    val animatedValue by animateFloatAsState(
        targetValue = value / 100f,
        animationSpec = tween(600, easing = EaseOutCubic),
        label = "stat_$label"
    )

    val dangerLevel = if (dangerWhenHigh) {
        when {
            value >= 80 -> 2
            value >= 60 -> 1
            else        -> 0
        }
    } else {
        when {
            value <= 20 -> 2
            value <= 40 -> 1
            else        -> 0
        }
    }

    val barGradient = when (dangerLevel) {
        2    -> listOf(Color(0xFF8B1A1A), Color(0xFFCC2222))
        1    -> listOf(Color(0xFF7A5500), Color(0xFFBB8800))
        else -> gradient
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse_$label")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue  = if (dangerLevel == 2) 0.6f else 1f,
        animationSpec = infiniteRepeatable(
            animation   = tween(600, easing = EaseInOutSine),
            repeatMode  = RepeatMode.Reverse
        ),
        label = "pulse_val_$label"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = icon, fontSize = 18.sp, modifier = Modifier.width(24.dp))

        Text(
            text = label.uppercase(),
            fontFamily = Cinzel,
            fontSize = 12.sp,
            color = when (dangerLevel) {
                2    -> Color(0xFFCC2222)
                1    -> Color(0xFFBB8800)
                else -> TextMuted
            },
            letterSpacing = 1.sp,
            modifier = Modifier.width(80.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(7.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Color(0xFF1A1228))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(animatedValue)
                    .alpha(pulse)
                    .background(Brush.horizontalGradient(barGradient))
            )
        }

        Text(
            text = "$value",
            fontFamily = Cinzel,
            fontSize = 13.sp,
            color = when (dangerLevel) {
                2    -> Color(0xFFCC2222)
                1    -> Color(0xFFBB8800)
                else -> TextSecond
            },
            modifier = Modifier.width(32.dp),
            textAlign = TextAlign.End,
        )
    }
}

//  EldritchMessageBox

@Composable
fun EldritchMessageBox(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFF18102A))
            .border(0.5.dp, BorderSubtle, RoundedCornerShape(6.dp))
            .padding(horizontal = 18.dp, vertical = 14.dp)
    ) {
        Text(
            text = "\u275D",
            fontSize = 20.sp,
            color = BorderAccent,
            modifier = Modifier.align(Alignment.TopStart).offset(y = (-4).dp)
        )
        Text(
            text = message,
            fontFamily = CrimsonText,
            fontSize = 16.sp,
            color = TextSecond,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp)
        )
    }
}

// EldritchButton

@Composable
fun EldritchButton(
    icon: String,
    label: String,
    modifier: Modifier = Modifier,
    horizontal: Boolean = false,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "btn_scale_$label"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isPressed) BorderAccent else BorderSubtle,
        label = "btn_border_$label"
    )
    val bgColor by animateColorAsState(
        targetValue = if (isPressed) BgCardPressed else BgCard,
        label = "btn_bg_$label"
    )

    val content: @Composable () -> Unit = {
        Text(text = icon, fontSize = if (horizontal) 24.sp else 22.sp)
        if (horizontal) Spacer(Modifier.width(10.dp)) else Spacer(Modifier.height(5.dp))
        Text(
            text = label.uppercase(),
            fontFamily = Cinzel,
            fontSize = if (horizontal) 15.sp else 11.sp,
            color = TextSecond,
            letterSpacing = 1.sp,
        )
    }

    val baseModifier = modifier
        .scale(scale)
        .clip(RoundedCornerShape(6.dp))
        .background(bgColor)
        .border(0.5.dp, borderColor, RoundedCornerShape(6.dp))
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
        .padding(vertical = if (horizontal) 16.dp else 14.dp,
            horizontal = if (horizontal) 20.dp else 0.dp)

    if (horizontal) {
        Row(
            modifier = baseModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) { content() }
    } else {
        Column(
            modifier = baseModifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) { content() }
    }
}
// Preview

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF12101A)
@Composable
private fun HubScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        StarfieldBackground()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 20.dp, bottom = 160.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "CTHULHU", fontFamily = Cinzel, fontWeight = FontWeight.SemiBold, fontSize = 36.sp, letterSpacing = 5.sp, color = TextPrimary, textAlign = TextAlign.Center)
            Spacer(Modifier.height(4.dp))
            Text(text = "Dreamer of the Deep", fontFamily = CrimsonText, fontSize = 16.sp, color = TextMuted, fontStyle = FontStyle.Italic, letterSpacing = 1.sp)
            Spacer(Modifier.height(20.dp))
            AgeBar(age = 9, maxAge = 50)
            Spacer(Modifier.height(24.dp))
            EldritchStatBar(label = "Fome",    value = 35, gradient = GradHunger,   icon = "🍖")
            EldritchStatBar(label = "Felicidade", value = 72, gradient = GradHappy,    icon = "😊")
            EldritchStatBar(label = "Cansaço", value = 20, gradient = GradTired,    icon = "😴")
            EldritchStatBar(label = "Banheiro",  value = 50, gradient = GradBathroom, icon = "🚽")
            EldritchStatBar(label = "Sujeira", value = 15, gradient = GradDirt,     icon = "🧼")
            Spacer(Modifier.height(16.dp))
            EldritchMessageBox(message = "Yummy yummy souls! Cthulhu wants more!!")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp)
                .padding(bottom = 20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                EldritchButton(icon = "🍖", label = "Alimentar",  modifier = Modifier.weight(1f), onClick = {})
                EldritchButton(icon = "🎮", label = "Jogar",  modifier = Modifier.weight(1f), onClick = {})
                EldritchButton(icon = "💤", label = "Descansar",  modifier = Modifier.weight(1f), onClick = {})
            }
            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                EldritchButton(icon = "🛁", label = "Banho",    modifier = Modifier.weight(1f), onClick = {})
                EldritchButton(icon = "🚽", label = "Banheiro", modifier = Modifier.weight(1f), onClick = {})
            }
        }
    }
}