package components

import react.*
import react.dom.*

class Game : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            +"Game"
        }
    }
}

fun RBuilder.game(handler: RProps.() -> Unit): ReactElement {
    return child(Game::class) {
        this.attrs(handler)
    }
}