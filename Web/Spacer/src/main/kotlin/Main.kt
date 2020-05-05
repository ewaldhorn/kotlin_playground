import org.w3c.dom.*
import org.w3c.dom.events.*
import kotlin.browser.*

val canvas = document.getElementById("game") as HTMLCanvasElement
val context = canvas.getContext("2d") as CanvasRenderingContext2D
val spaceShipSize = SpaceShipSize(20.0, 30.0)
var spaceShipPosition = SpaceShipPosition(200.0, 200.0)
val gravity = 0.0
val thrust = 5.0
val spaceShip = SpaceShip(spaceShipSize, spaceShipPosition)

fun main() {
    setupEventHandlers()
    draw()
}

fun setupEventHandlers() {
    document.addEventListener("keydown", {e -> handleKeyInput(e as KeyboardEvent)})
    document.addEventListener("keyup", {e -> handleKeyInput(e as KeyboardEvent)})
}

class SpaceShip(val size: SpaceShipSize, var position: SpaceShipPosition) {
    var color : String = "white"
    var angle = 0.0
    var engineOn = false
    var rotatingLeft = false
    var rotatingRight = false
    var velocity = Velocity()

    fun draw() {
        val triangleCenterX  = position.x + 0.5  * size.width
        val triangleCenterY = position.y + 0.5 * size.height

        context.save()

        context.translate(triangleCenterX, triangleCenterY)
        context.rotate(angle)
        context.lineWidth = 1.0
        context.beginPath()

        context.moveTo(0.0, -size.height / 2)
        context.lineTo(-size.width / 2, size.height / 2)
        context.lineTo(size.width / 2, size.height / 2)

        context.closePath()

        context.strokeStyle = color
        context.stroke()

        context.restore()
    }

    fun moveSpaceShip() {
        // convert to radians
        val degToRad = kotlin.math.PI / 180
        position.x += velocity.x
        position.y += velocity.y

        if (spaceShip.rotatingLeft) {
            angle -= degToRad
        }

        if (spaceShip.rotatingRight) {
            angle += degToRad
        }

        if (spaceShip.engineOn) {
            velocity.x += (thrust / 100) * kotlin.math.sin(angle)
            velocity.y -= (thrust / 100) * kotlin.math.cos(angle)
        }

        velocity.y += gravity / 100

    }
}

fun handleKeyInput(event: KeyboardEvent) {
    var isKeyDown = false

    when (event.type) {
        "keydown" -> {
            isKeyDown = true
        }
        else -> {
            isKeyDown = false
        }
    }

    console.log("The key code is ${event.code}")

    when (event.code) {
        "ArrowLeft" -> {
            spaceShip.rotatingLeft = isKeyDown
        }
        "ArrowUp" -> {
            spaceShip.engineOn = isKeyDown
        }
        "ArrowRight" -> {
            spaceShip.rotatingRight = isKeyDown
        }
        else -> {
        }
    }
}

fun draw() {
    context.fillStyle = "#000000"
    context.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
    spaceShip.moveSpaceShip()
    spaceShip.draw()
    window.requestAnimationFrame { draw() }
}

class Velocity {
    var x = 0.0
    var y = 0.0
}

data class SpaceShipSize(val width: Double, val height: Double)

class SpaceShipPosition(var x: Double = 0.0, var y: Double = 0.0)
