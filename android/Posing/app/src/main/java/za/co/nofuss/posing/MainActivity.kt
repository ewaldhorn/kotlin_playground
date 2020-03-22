package za.co.nofuss.posing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Border
import androidx.ui.graphics.Color
import androidx.ui.layout.LayoutAlign
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MaterialTheme {
        Surface(color = Color.Gray, border = Border(1.dp, Color.Black)) {
            Greeting(name = "Android")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = LayoutPadding(24.dp) + LayoutAlign.CenterHorizontally
    )
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp()
}