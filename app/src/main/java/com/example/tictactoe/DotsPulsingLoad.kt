package com.example.tictactoe

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

val dotSize = 24.dp
const val delayUnit = 300

@Composable
fun DotPulsing() {
    @Composable
    fun Dot(
        scale: Float
    ) = Spacer(
        modifier = Modifier
            .size(dotSize)
            .scale(scale)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
    )

    val infiniteTransition = rememberInfiniteTransition(label = "Loading Next Move")

    @Composable
    fun AnimateScaleWithDelay(delay: Int) = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = delayUnit * 4
                0f at delay with LinearEasing
                1f at delay + delayUnit with LinearEasing
                1f at delay + delayUnit * 2

            }
        ), label = "Loading Next Move"
    )

    val scale1 by AnimateScaleWithDelay(0)
    val scale2 by AnimateScaleWithDelay(delayUnit)
    val scale3 by AnimateScaleWithDelay(delayUnit * 2)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val spaceSize = 2.dp
        Dot(scale = scale1)
        Spacer(modifier = Modifier.width(spaceSize))
        Dot(scale = scale2)
        Spacer(modifier = Modifier.width(spaceSize))
        Dot(scale = scale3)
    }
}


