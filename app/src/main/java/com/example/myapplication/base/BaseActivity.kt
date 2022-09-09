package com.example.myapplication.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

                }
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name), fontSize = 18.sp)
    }, backgroundColor = colorResource(id = R.color.purple_700), contentColor = Color.White)
}

@Composable
fun TopBar(title: String) {
    TopAppBar(title = {
        Text(text = title, fontSize = 18.sp)
    }, backgroundColor = colorResource(id = R.color.purple_700), contentColor = Color.White)
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Composable
fun AppTheme(isDarkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(colors = if (isDarkMode) DarkColors else LightColors, content = content)
}

private val Black = Color(0xffff1122)
private val Blue200 = Color(0xff91a4fc)

private val DarkColors = darkColors(
    primary = Black,
    secondary = Blue200
)

private val LightColors = darkColors(
    primary = Blue200,
    secondary = Color.White
)