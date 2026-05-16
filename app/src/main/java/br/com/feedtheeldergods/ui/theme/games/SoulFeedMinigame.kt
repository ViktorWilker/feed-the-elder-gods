package br.com.feedtheeldergods.ui.theme

import android.media.SoundPool
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.feedtheeldergods.GameViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import android.R.raw
import androidx.compose.ui.platform.LocalContext

private data class Soul(
    val id: Int,
    val xFraction: Float,
    val yFraction: Float,
    val lifespanMs: Long,
    val spawnTime: Long,
)

@Composable
fun SoulFeedMinigame(
    viewModel: GameViewModel,
    onDone: () -> Unit,
) {
    val god = viewModel.currentGod ?: return
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(4)
            .build()
    }
    val soundId = remember {
        soundPool.load(context, br.com.feedtheeldergods.R.raw.soul_bloop, 1)
    }
    DisposableEffect(Unit) {
        onDispose { soundPool.release() }
    }
    val gameDurationMs = 10_000L
    var timeLeftMs by remember { mutableStateOf(gameDurationMs) }
    var score by remember { mutableStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }
    var souls by remember { mutableStateOf(listOf<Soul>()) }
    var nextId by remember { mutableStateOf(0) }
    var areaSize by remember { mutableStateOf(IntSize.Zero) }

    LaunchedEffect(Unit) {
        val startTime = System.currentTimeMillis()
        var lastSpawnTime = startTime

        while (timeLeftMs > 0) {
            delay(50)
            val now = System.currentTimeMillis()
            val elapsed = now - startTime
            timeLeftMs = (gameDurationMs - elapsed).coerceAtLeast(0)

            val progress = elapsed / gameDurationMs.toFloat()

            val lifespan = (3500 - (progress * 1700)).toLong().coerceAtLeast(1800)

            val spawnInterval = (400 - (progress * 200)).toLong().coerceAtLeast(200)

            souls = souls.filter { now - it.spawnTime < it.lifespanMs }

            if (now - lastSpawnTime >= spawnInterval && areaSize != IntSize.Zero) {
                souls = souls + Soul(
                    id = nextId++,
                    xFraction = Random.nextFloat() * 0.8f + 0.1f,
                    yFraction = Random.nextFloat() * 0.8f + 0.1f,
                    lifespanMs = lifespan,
                    spawnTime = now,
                )
                lastSpawnTime = now
            }
        }
        gameOver = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDeep)
    ) {
        StarfieldBackground()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // header
            Spacer(Modifier.height(48.dp))
            Text(
                text = "feed ${god.name}!",
                fontFamily = Cinzel,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = TextPrimary,
                letterSpacing = 1.sp,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "tap the souls before they escape",
                fontFamily = CrimsonText,
                fontSize = 14.sp,
                color = TextMuted,
            )
            Spacer(Modifier.height(12.dp))

            // timer + score bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // timer
                val timerColor = if (timeLeftMs < 5000) Color(0xFFC4522A) else TextPrimary
                Text(
                    text = "${(timeLeftMs / 1000L) + 1}s",
                    fontFamily = Cinzel,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = timerColor,
                )
                // score
                Text(
                    text = "👻 $score",
                    fontFamily = Cinzel,
                    fontSize = 20.sp,
                    color = TextPrimary,
                )
            }

            Spacer(Modifier.height(8.dp))

            // barra de tempo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(BorderSubtle)
            ) {
                val barColor = if (timeLeftMs < 5000) Color(0xFFC4522A) else Color(0xFF8B5AB4)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(timeLeftMs / gameDurationMs.toFloat())
                        .background(barColor)
                )
            }

            Spacer(Modifier.height(16.dp))

            // área do jogo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF0D0B14))
                    .border(0.5.dp, BorderSubtle, RoundedCornerShape(12.dp))
                    .onGloballyPositioned { areaSize = it.size }
                    .pointerInput(Unit) {                          // ← era .pointerInput(souls)
                        detectTapGestures { offset ->
                            if (gameOver) return@detectTapGestures
                            val hitRadius = 80f
                            val hit = souls.firstOrNull { soul ->
                                val sx = soul.xFraction * areaSize.width
                                val sy = soul.yFraction * areaSize.height
                                val dx = offset.x - sx
                                val dy = offset.y - sy
                                (dx * dx + dy * dy) < (hitRadius * hitRadius)
                            }
                            if (hit != null) {
                                souls = souls - hit
                                score++
                                soundPool.play(soundId, 1f, 1f, 0, 0, 1f)  // ← som
                            }
                        }
                    }
            ) {
                souls.forEach { soul ->
                    key(soul.id) {
                        SoulEntity(
                            soul = soul,
                            areaSize = areaSize,
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
        }

        // game over overlay
        if (gameOver) {
            val hungerReduction = (score * 3).coerceAtMost(60)

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
                            score >= 15 -> "glorious feast!"
                            score >= 8  -> "satisfying..."
                            else        -> "pathetic offering."
                        },
                        fontFamily = Cinzel,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 26.sp,
                        color = TextPrimary,
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.sp,
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "$score souls consumed\nhunger −$hungerReduction",
                        fontFamily = CrimsonText,
                        fontSize = 17.sp,
                        color = TextSecond,
                        textAlign = TextAlign.Center,
                        lineHeight = 26.sp,
                    )
                    Spacer(Modifier.height(36.dp))
                    EldritchButton(
                        icon = "✔️",
                        label = "return",
                        horizontal = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        onClick = {
                            scope.launch {
                                viewModel.feedSouls(score)
                                onDone()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SoulEntity(
    soul: Soul,
    areaSize: IntSize,
) {
    if (areaSize == IntSize.Zero) return

    val now = remember { System.currentTimeMillis() }
    val age = (now - soul.spawnTime).toFloat() / soul.lifespanMs.toFloat()

    // alpha: aparece rápido, some no final
    val alpha by animateFloatAsState(
        targetValue = if (age > 0.75f) 0f else 1f,
        animationSpec = tween(
            durationMillis = if (age > 0.75f) ((soul.lifespanMs * 0.25f).toInt()) else 150
        ),
        label = "soul_alpha_${soul.id}"
    )

    // pulsa levemente
    val infiniteTransition = rememberInfiniteTransition(label = "soul_pulse_${soul.id}")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "soul_scale_${soul.id}"
    )

    val xPx = soul.xFraction * areaSize.width
    val yPx = soul.yFraction * areaSize.height

    Box(
        modifier = Modifier
            .offset(
                x = with(androidx.compose.ui.platform.LocalDensity.current) { xPx.toDp() } - 28.dp,
                y = with(androidx.compose.ui.platform.LocalDensity.current) { yPx.toDp() } - 28.dp,
            )
            .size(56.dp)
            .scale(scale)
            .alpha(alpha)
            .clip(CircleShape)
            .background(Color(0x22FFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "👻", fontSize = 28.sp)
    }
}