import constants.*
import kotlin.browser.*

fun main() {
    console.log("Hello from Kotlin")
    updateTitleWithAppVersion()
}

fun updateTitleWithAppVersion() {
    document.title = document.title.plus(" v${Constants.appVersion}")
}
