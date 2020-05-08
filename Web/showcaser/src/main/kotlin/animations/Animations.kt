package animations

import org.w3c.dom.*
import kotlin.browser.*
import kotlin.dom.*

@JsName("startCSSAnimationSample")
fun startCSSAnimationSample() {
    clearLoadingForElement(document.getElementById("p1"), 2000)

    for (i in 1..18) {
        clearLoadingForElement(document.getElementById("l$i"),
            2250 + kotlin.random.Random.nextInt(5000))
    }
}

fun clearLoadingForElement(el: Element?, delay: Int) {
    el?.let {
        window.setTimeout({
            it.removeClass("loading")
        }, delay)
    }
}
