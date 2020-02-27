package snake

import platform.darwin.box
import platform.darwin.curs_set
import platform.darwin.delwin
import platform.darwin.endwin
import platform.darwin.halfdelay
import platform.darwin.initscr
import platform.darwin.mvwprintw
import platform.darwin.newwin
import platform.darwin.noecho
import platform.darwin.wclear
import platform.darwin.wgetch
import platform.darwin.wrefresh

val windowWidth = 20
val windowHeight = 10

fun main() {
    initscr()
    noecho()
    curs_set(0)
    halfdelay(2)

    val window = newwin(windowHeight + 2, windowWidth + 2, 0, 0)
    var snake = Snake(cells = listOf(Cell(2,0), Cell(1, 0), Cell(0,0)), direction = Direction.right)

    var input = 0
    while (input.toChar() != 'q') {
        wclear(window)

        box(window, 0, 0)

        snake.tail.forEach {
            mvwprintw(window, it.y + 1, it.x + 1, "o")
        }

        snake.head.let {mvwprintw(window, it.y + 1, it.x + 1, "Q")}

        wrefresh(window)

        input = wgetch(window)

        snake = snake.move()
    }

    delwin(window)
    endwin()
}

data class Snake(val cells: List<Cell>, val direction: Direction) {
    val head = cells.first()
    val tail = cells.subList(1, cells.size)

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