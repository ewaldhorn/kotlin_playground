import constants.*
import kotlin.browser.*

fun main() {
    updateTitleWithAppVersion()
}

fun updateTitleWithAppVersion() {
    document.title = document.title.plus(" v${Constants.appVersion}")
}
