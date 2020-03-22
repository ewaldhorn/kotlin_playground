package za.co.nofuss.posing.codelab

import android.content.Context
import android.content.Intent
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.Border
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutAlign
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Row
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import za.co.nofuss.posing.activity.ProfileActivity
import za.co.nofuss.posing.models.CounterState
import za.co.nofuss.posing.theme.MyAppTheme

@Composable
fun MyApp(children: @Composable() () -> Unit) {
    MyAppTheme {
        Surface(color = Color.Gray, border = Border(1.dp, Color.Black)) {
            children()
        }
    }
}

@Composable
fun MyScreenContent(
    names: List<String> = listOf("Android", "Again"),
    counterState: CounterState = CounterState(),
    context: Context? = null
) {
    Column(modifier = LayoutHeight.Fill) {
        Column(modifier = LayoutWeight(1f)) {
            for (name in names) {
                Greeting(name = name)
                Divider(color = Color.Black)
            }
            Divider(color = Color.Transparent, height = 32.dp)
        }
        Row(modifier = LayoutWidth.Fill + LayoutAlign.CenterHorizontally) {
            Button(onClick = {
                context?.startActivity(Intent(context, ProfileActivity::class.java))
            }, modifier = LayoutPadding(bottom = 36.dp)) {
                Text(text = "To Profile")
            }
        }
        Row(modifier = LayoutWidth.Fill + LayoutAlign.CenterHorizontally) {
            Counter(state = counterState)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography().h1.copy(fontSize = 30.sp),
        modifier = LayoutPadding(24.dp) + LayoutAlign.CenterHorizontally
    )
}

@Composable
fun Counter(state: CounterState) {
    Button(onClick = { state.count++ }, modifier = LayoutPadding(bottom = 10.dp)) {
        Text(text = "Button clicks: ${state.count}")
    }
}