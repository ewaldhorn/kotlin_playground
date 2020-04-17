import app.*
import react.dom.*
import kotlin.browser.*

fun main() {
    render(document.getElementById("root")) {
        child(App::class) {}
    }
}