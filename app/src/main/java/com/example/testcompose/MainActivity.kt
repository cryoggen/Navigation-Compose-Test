package com.example.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testcompose.ui.theme.TestComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StartApp()
                }
            }
        }
    }
}

@Composable
fun StartApp() {
    val navController = rememberNavController()
    NavHost(navController)
}

@Composable
fun NavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "screen_one",
        modifier = modifier
    ) {
        composable("screen_one") {
            StartScreen(
                text = stringResource(R.string.screen_one_text),
                textButton = stringResource(R.string.button_screen_one),
                onClickStartNextScreen = { navController.navigate("screen_two") })
        }
        composable("screen_two") {
            StartScreen(
                text = stringResource(R.string.screen_two_text),
                textButton = stringResource(R.string.button_screen_two),
                onClickStartNextScreen = { navController.navigate("screen_three") })
        }
        composable("screen_three") {
            StartScreen(
                text = stringResource(R.string.screen_three_text),
                textButton = stringResource(R.string.button_screen_three),
                onClickStartNextScreen = { navController.navigate("screen_one") })
        }
    }
}

@Composable
fun StartScreen(text: String, textButton: String, onClickStartNextScreen: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Text(text = text)
        Button(onClick = onClickStartNextScreen) {
            Text(text = textButton)
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    TestComposeTheme {
        StartApp()
    }
}