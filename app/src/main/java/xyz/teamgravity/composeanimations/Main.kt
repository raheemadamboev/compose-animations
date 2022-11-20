package xyz.teamgravity.composeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
                    AnimatedVisibilityDemo()
                }
            }
        }
    }

    @Composable
    fun AnimatedVisibilityDemo() {
        Column(
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
}