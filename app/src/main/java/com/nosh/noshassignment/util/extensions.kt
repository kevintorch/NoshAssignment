package com.nosh.noshassignment.util

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


fun PaddingValues.merge(
    other: PaddingValues,
    layoutDirection: LayoutDirection = LayoutDirection.Ltr
): PaddingValues =
    PaddingValues(
        start = this.calculateStartPadding(layoutDirection) + other.calculateStartPadding(
            layoutDirection
        ),
        top = this.calculateTopPadding() + other.calculateTopPadding(),
        end = this.calculateEndPadding(layoutDirection) + other.calculateEndPadding(
            layoutDirection
        ),
        bottom = this.calculateBottomPadding() + other.calculateBottomPadding()
    )
@Composable
fun PaddingValues.union(other: PaddingValues): PaddingValues {
    val dir = LocalLayoutDirection.current
    return PaddingValues(
        start = maxOf(this.calculateStartPadding(dir), other.calculateStartPadding(dir)),
        top = maxOf(this.calculateTopPadding(), other.calculateTopPadding()),
        end = maxOf(this.calculateEndPadding(dir), other.calculateEndPadding(dir)),
        bottom = maxOf(this.calculateBottomPadding(), other.calculateBottomPadding())
    )
}

fun PaddingValues.merge(horizontal: Dp = 0.dp, vertical: Dp = 0.dp) =
    merge(PaddingValues(horizontal = horizontal, vertical = vertical))

fun PaddingValues.merge(all: Dp) =
    merge(PaddingValues(all = all))

fun PaddingValues.merge(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp
) = merge(PaddingValues(start = start, top = top, end = end, bottom = bottom))

@Composable
fun WindowInsets.safeLeft(): Dp {
    return WindowInsets.safeContent.asPaddingValues().calculateStartPadding(LocalLayoutDirection.current)
}

@Composable
fun WindowInsets.safeRight(): Dp {
    return WindowInsets.safeContent.asPaddingValues().calculateEndPadding(LocalLayoutDirection.current)
}

@Composable
fun Modifier.safeHorizontalPadding(): Modifier {
    val start = WindowInsets.safeContent.getLeft(LocalDensity.current, LocalLayoutDirection.current)
    val end = WindowInsets.safeContent.getRight(LocalDensity.current, LocalLayoutDirection.current)
    return Modifier
        .padding(start = start.dp, end = end.dp)
        .then(this)
}

@Composable
fun Modifier.safeVerticalPadding(): Modifier {
    val top = WindowInsets.safeContent.getTop(LocalDensity.current)
    val bottom = WindowInsets.safeContent.getBottom(LocalDensity.current)
    return this.padding(start = top.dp, end = bottom.dp)
}

@Composable
fun Modifier.safeTopPadding(): Modifier {
    val top = WindowInsets.safeContent.getTop(LocalDensity.current)
    return Modifier
        .padding(top = top.dp)
        .then(this)
}

@Composable
fun Modifier.safeBottomPadding(): Modifier {
    val bottom = WindowInsets.safeContent.getBottom(LocalDensity.current)
    return this.padding(bottom = bottom.dp)
}

@Composable
fun Modifier.doublePulseEffect(
    targetScale: Float = 2f,
    initialScale: Float = 1f,
    shape: Shape = CircleShape,
    color: Color = DefaultPulseColor,
    duration: Int = 1200,
): Modifier = doublePulseEffect(targetScale, initialScale, SolidColor(color), shape, duration)


@Composable
fun Modifier.doublePulseEffect(
    targetScale: Float = 1.5f,
    initialScale: Float = 1f,
    brush: Brush = SolidColor(DefaultPulseColor),
    shape: Shape = CircleShape,
    duration: Int = 1200,
): Modifier {
    return this
        .pulseEffect(
            targetScale, initialScale, brush, shape,
            animationSpec = tween(duration, easing = FastOutSlowInEasing)
        )
        .pulseEffect(
            targetScale, initialScale, brush, shape,
            animationSpec = tween(
                durationMillis = (duration * 0.7f).toInt(),
                delayMillis = (duration * 0.3f).toInt(),
                easing = LinearEasing
            )
        )
}

@Composable
fun Modifier.pulseEffect(
    targetScale: Float = 1.5f,
    initialScale: Float = 1f,
    color: Color = DefaultPulseColor,
    shape: Shape = CircleShape,
    animationSpec: DurationBasedAnimationSpec<Float> = tween(1200)
): Modifier = pulseEffect(targetScale, initialScale, SolidColor(color), shape, animationSpec)

@Composable
fun Modifier.pulseEffect(
    targetScale: Float = 1.5f,
    initialScale: Float = 1f,
    brush: Brush = SolidColor(DefaultPulseColor),
    shape: Shape = CircleShape,
    animationSpec: DurationBasedAnimationSpec<Float> = tween(1200)
): Modifier {
    val pulseTransition = rememberInfiniteTransition("PulseTransition")
    val pulseScale by pulseTransition.animateFloat(
        initialValue = initialScale,
        targetValue = targetScale,
        animationSpec = infiniteRepeatable(animationSpec),
        label = "PulseScale"
    )
    val pulseAlpha by pulseTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(animationSpec),
        label = "PulseAlpha"
    )
    return this
        .drawBehind {
            val outline = shape.createOutline(size, layoutDirection, this)
            scale(pulseScale) {
                drawOutline(outline, brush, pulseAlpha)
            }
        }
}

private val DefaultPulseColor = Color.Black.copy(0.32f)