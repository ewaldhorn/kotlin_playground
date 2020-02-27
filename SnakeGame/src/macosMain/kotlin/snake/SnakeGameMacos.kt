package snake

import platform.darwin.box
import platform.darwin.delwin
import platform.darwin.endwin
import platform.darwin.initscr
import platform.darwin.mvwprintw
import platform.darwin.newwin
import platform.darwin.wgetch
import platform.darwin.wrefresh

val windowWidth = 20
val windowHeight = 10

fun main() {
    initscr()

    val window = newwin(windowHeight, windowWidth, 0, 0)
    box(window, 0, 0)
    mvwprintw(window, 3, 2, "ooooQ")
    wrefresh(window)

    wgetch(window)
    wgetch(window)

    delwin(window)
    endwin()
}

data class Snake(val cells: List<Cell>, val direction: Direction) {
    fun move(): Snake {
        val newHead = cells.first().move(direction)
        val newTail = cells.dropLast(1)
        return copy(cells = listOf(newHead) + newTail)
    }
}

data class Cell(val x: Int, val y: Int) {
    fun move(direction: Direction) = Cell(x + direction.dx, y + direction.dy)
}

enum class Direction(val dx: Int, val dy: Int) {
    up(0, -1), down(0, 1), left(-1, 0), right(1, 0)
}