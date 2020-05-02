package components

import react.*
import react.dom.*

class Square : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            +"Square"
        }
    }
}

fun RBuilder.square(handler: RProps.() -> Unit): ReactElement {
    return child(Square::class) {
        this.attrs(handler)
    }
}