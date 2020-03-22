package za.co.nofuss.posing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.tooling.preview.Preview
import za.co.nofuss.posing.codelab.MyApp
import za.co.nofuss.posing.codelab.MyScreenContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent(context = this)
            }
        }
    }
}

@Preview("My Standard Preview", widthDp = 350, heightDp = 750)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}