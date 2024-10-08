package org.example.project.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ls.notey.utils.SALMON

@Composable
fun TopBar(
    isDetailsScreen: Boolean = false,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        if (isDetailsScreen) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                "Back button",
                modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = SALMON)
                ) { onBackClick() })
        }
        if (!isDetailsScreen) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = "Notes",
                fontSize = 36.sp
            )

            Icon(
                imageVector = Icons.Filled.Add,
                "Add button",
                modifier = Modifier.size(32.dp).clip(RoundedCornerShape(12.dp)).clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = SALMON)
                ) {
                    onAddClick()
                }
            )
        }
    }
}
