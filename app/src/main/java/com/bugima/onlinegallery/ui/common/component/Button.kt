package com.bugima.onlinegallery.ui.common.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bugima.onlinegallery.util.Const

@Composable
fun IconButton(
    icon: ImageVector,
    iconSize: Dp = 32.dp,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = Color.Transparent)
            .clickable { onClick() }
    ) {
        Image(
            imageVector = icon,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            contentDescription = null,
            modifier = Modifier.size(iconSize),
        )
    }
}

@Composable
fun IconFAB(
    icon: ImageVector,
    iconSize: Dp = 48.dp,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(48.dp)
            .clip(shape = RoundedCornerShape(percent = 50))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clickable { onClick() }
    ) {
        Image(
            imageVector = icon,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer),
            contentDescription = null,
            modifier = Modifier.size(iconSize),
        )
    }
}

@Composable
fun CommonSpinningIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    size: Dp = 48.dp,
    color: Color,
    iconColor: Color,
    onClicked: () -> Unit
) {
    val angle = remember { mutableFloatStateOf(0f) }
    val animatedAngle by animateFloatAsState(
        targetValue = angle.floatValue,
        animationSpec = tween(durationMillis = Const.SPIN_DURATION),
        label = "spinningAnimation"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .clip(shape = RoundedCornerShape(size / 2))
            .background(color)
            .clickable {
                angle.floatValue += Const.SPIN_DEGREE
                onClicked()
            }
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(iconColor),
            modifier = Modifier
                .size(32.dp)
                .graphicsLayer(rotationZ = animatedAngle)
        )
    }
}
