package components

import kotlinx.html.*
import kotlinx.html.js.*
import react.*
import react.dom.*

class Board : RComponent<BoardProps, RState>() {
    override fun RBuilder.render() {
        div(classes = "board") {
            props.squares.mapIndexed { index, square ->
                square {
                    +"$index  $square"
                    key = index.toString()
                    attrs {
                        onClickFunction = {
                            props.onClick(index)
                        }
                        value = square.props.value
                    }
                }
            }
        }
    }
}

external interface BoardProps: RProps {
    var squares: List<Square>
    var onClick: (Int) -> Unit
}

fun RBuilder.board(handler: BoardProps.() -> Unit): ReactElement {
    return child(Board::class) {
        this.attrs(handler)
    }
}