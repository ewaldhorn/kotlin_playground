package snake

import kotlin.test.Test
import kotlin.test.assertEquals

class SnakeTests {
    @Test
    fun `tests that snake can move to the right`() {
        val snake = Snake(cells = listOf(Cell(2,0), Cell(1, 0), Cell(0,0)), direction = Direction.right)
        assertEquals(actual = snake.move(), expected = Snake(cells = listOf(Cell(3,0), Cell(2, 0), Cell(1,0)), direction = Direction.right))
    }
}