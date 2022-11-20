package xyz.teamgravity.composeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.teamgravity.composeanimations.ui.theme.ComposeAnimationsTheme

class Main : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    AnimatedVisibilityDemo()
//                    AnimateAsStateDemo()
                    UpdateTransitionDemo()
                }
            }
        }
    }

    @Composable
    fun AnimatedVisibilityDemo() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var visible by remember { mutableStateOf(false) }

            Button(
                onClick = {
                    visible = !visible
                }
            ) {
                Text(text = stringResource(id = R.string.toggle))
            }
            AnimatedVisibility(
                visible = visible,
                enter = slideInHorizontally() + fadeIn(),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            ) {
                Box(modifier = Modifier.background(Color.Red))
            }
        }
    }

    @Composable
    fun AnimateAsStateDemo() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var round by remember { mutableStateOf(false) }
            val borderRadius by animateIntAsState(
                targetValue = if (round) 100 else 0,
                animationSpec = tween(
                    durationMillis = 3_000,
                    delayMillis = 500
                )
            )

            Button(
                onClick = {
                    round = !round
                }
            ) {
                Text(text = stringResource(id = R.string.toggle))
            }
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(percent = borderRadius))
                    .background(Color.Red)
            )
        }
    }

    @Composable
    fun UpdateTransitionDemo() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var round by remember { mutableStateOf(false) }
            val transition = updateTransition(
                targetState = round,
                label = null
            )
            val borderRadius by transition.animateInt(
                transitionSpec = { tween(durationMillis = 2_000) },
                label = "borderRadius",
                targetValueByState = { rounded ->
                    if (rounded) 100 else 0
                }
            )
            val color by transition.animateColor(
                transitionSpec = { tween(durationMillis = 1_000) },
                label = "color",
                targetValueByState = { rounded ->
                    if (rounded) Color.Green else Color.Red
                }
            )

            Button(
                onClick = {
                    round = !round
                }
            ) {
                Text(text = stringResource(id = R.string.toggle))
            }
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(percent = borderRadius))
                    .background(color)
            )
        }
    }
}