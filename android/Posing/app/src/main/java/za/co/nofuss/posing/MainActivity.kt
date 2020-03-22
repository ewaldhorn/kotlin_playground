package za.co.nofuss.posing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Border
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutAlign
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import za.co.nofuss.posing.models.CounterState

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(children: @Composable() () -> Unit) {
    MaterialTheme {
        Surface(color = Color.Gray, border = Border(1.dp, Color.Black)) {
            children()
        }
    }
}

@Composable
fun MyScreenContent(
    names: List<String> = listOf("Android", "Again"),
    counterState: CounterState = CounterState()
) {
    Column(modifier = LayoutHeight.Fill) {
        Column(modifier = LayoutWeight(1f)) {
            for (name in names) {
                Greeting(name = name)
                Divider(color = Color.Black)
            }
            Divider(color = Color.Transparent, height = 32.dp)
        }
        Counter(state = counterState)
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = LayoutPadding(24.dp) + LayoutAlign.CenterHorizontally
    )
}

@Composable
fun Counter(state: CounterState) {
    Surface(
        modifier = LayoutAlign.CenterHorizontally + LayoutWidth.Fill + LayoutPadding(5.dp),
        color = Color.Transparent
    ) {
        Button(onClick = { state.count++ }, modifier = LayoutAlign.CenterHorizontally) {
            Text(text = "Button clicks: ${state.count}")
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}