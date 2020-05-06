package components

import kotlinx.html.js.*
import react.*
import react.dom.*

class Square : RComponent<SquareProps, RState>() {
    override fun RBuilder.render() {
        div {
            button(classes = "square-button") {
                attrs {
                    onClickFunction = {
                        props.onClick()
                    }
                    +props.value
                }
            }
        }
    }
}

external interface SquareProps: RProps {
    var value: String
    var onClick: () -> Unit
}

fun RBuilder.square(handler: SquareProps.() -> Unit): ReactElement {
    return child(Square::class) {
        this.attrs(handler)
    }
}