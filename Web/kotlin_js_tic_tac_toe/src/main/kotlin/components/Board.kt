package components

import react.*
import react.dom.*

class Board : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            +"Board"
        }
    }
}

fun RBuilder.board(handler: RProps.() -> Unit): ReactElement {
    return child(Board::class) {
        this.attrs(handler)
    }
}