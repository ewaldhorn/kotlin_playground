package components

import react.*
import react.dom.*

class Game : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div(classes = "game") {
            +"Game"
        }
    }
}

fun RBuilder.game(handler: RProps.() -> Unit): ReactElement {
    return child(Game::class) {
        this.attrs(handler)
    }
}

external interface GameState : RState {

}

fun calculateWinner(squares: List<Square>): String {
    var winner = ""

    for (i in 0..squares.size) {
        val line = squares[i]
        // if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
        //       return squares[a];
        console.log("$i  $line")
    }

    return winner
}

val lines = arrayOf(
    intArrayOf(0, 1, 2),
    intArrayOf(3, 4, 5),
    intArrayOf(6, 7, 8),
    intArrayOf(0, 3, 6),
    intArrayOf(1, 4, 7),
    intArrayOf(2, 5, 8),
    intArrayOf(0, 4, 8),
    intArrayOf(2, 4, 6)
)
