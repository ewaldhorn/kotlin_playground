package za.co.nofuss.posing.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutAlign
import androidx.ui.layout.LayoutWidth
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.sp

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Greeting2("Android")
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun ProfileScreen() {
    Column {
        TopAppBar(color = Color.Blue, title = {
            Text(
                style = MaterialTheme.typography().caption.copy(
                    color = Color.White,
                    fontSize = 20.sp
                ),
                text = "The App Bar Is Here",
                modifier = LayoutAlign.CenterHorizontally
            )
        }, modifier = LayoutAlign.CenterHorizontally + LayoutWidth.Fill)

        Greeting2(name = "Profile")
    }
}

@Preview("Profile Activity", widthDp = 350)
@Composable
fun DefaultPreview2() {
    MaterialTheme {
        ProfileScreen()
    }
}