package za.co.nofuss.graphicsinvestigations.ui.space_invaders

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView

class SpaceInvadersView(context: Context, private val size: Point) : SurfaceView(context),
    Runnable {

    private val gameThread = Thread(this)
    // A boolean which we will set and unset
    private var playing = false

    // Game is paused at the start
    private var paused = true

    // A Canvas and a Paint object
    private var canvas: Canvas = Canvas()
    private val paint: Paint = Paint()

    // The players ship
    private var playerShip: PlayerShip = PlayerShip(context, size.x, size.y)

    // Some Invaders
    private val invaders = ArrayList<Invader>()
    private var numInvaders = 0

    // The player's shelters are built from bricks
    private val bricks = ArrayList<DefenceBrick>()
    private var numBricks: Int = 0

    // The player's playerBullet
    // much faster and half the length
    // compared to invader's bullet
    private var playerBullet = Bullet(size.y, 1200f, 40f)

    // The invaders bullets
    private val invadersBullets = ArrayList<Bullet>()
    private var nextBullet = 0
    private val maxInvaderBullets = 10

    // The score
    private var score = 0

    // The wave number
    private var waves = 1

    // Lives
    private var lives = 3

    private var highScore = 0
    // How menacing should the sound be?
    private var menaceInterval: Long = 1000

    // Which menace sound should play next
    private var uhOrOh: Boolean = false
    // When did we last play a menacing sound
    private var lastMenaceTime = System.currentTimeMillis()

    private fun prepareLevel() {
        // Here we will initialize the game objects
        // Here we will initialize the game objects
        // Build an army of invaders
        Invader.numberOfInvaders = 0
        numInvaders = 0
        for (column in 0..10) {
            for (row in 0..5) {
                invaders.add(Invader(context,
                    row,
                    column,
                    size.x,
                    size.y))

                numInvaders++
            }
        }

        // Build the shelters
        numBricks = 0
        for (shelterNumber in 0..4) {
            for (column in 0..18) {
                for (row in 0..8) {
                    bricks.add(DefenceBrick(row,
                        column,
                        shelterNumber,
                        size.x,
                        size.y))

                    numBricks++
                }
            }
        }

        // Initialize the invadersBullets array
        for (i in 0 until maxInvaderBullets) {
            invadersBullets.add(Bullet(size.y))
        }
    }

    override fun run() {
        // This variable tracks the game frame rate
        var fps: Long = 0

        while (playing) {

            // Capture the current time
            val startFrameTime = System.currentTimeMillis()

            // Update the frame
            if (!paused) {
                update(fps)
            }

            // Draw the frame
            draw()

            // Calculate the fps rate this frame
            val timeThisFrame = System.currentTimeMillis() - startFrameTime
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame
            }
        }
    }

    private fun update(fps: Long) {
        // Update the state of all the game objects
        playerShip.update(fps)

        // Did an invader bump into the side of the screen
        var bumped = false

        // Has the player lost
        var lost = false

        // Update all the invaders if visible
        for (invader in invaders) {

            if (invader.isVisible) {
                // Move the next invader
                invader.update(fps)

                // If that move caused them to bump
                // the screen change bumped to true
                if (invader.position.left > size.x - invader.width
                    || invader.position.left < 0) {

                    bumped = true

                }
            }
        }

        // Update the players playerBullet if active
        if (playerBullet.isActive) {
            playerBullet.update(fps)
        }

        // Update all the invaders bullets if active
        for (bullet in invadersBullets) {
            if (bullet.isActive) {
                bullet.update(fps)
            }
        }

        // Did an invader bump into the edge of the screen
        if (bumped) {

            // Move all the invaders down and change direction
            for (invader in invaders) {
                invader.dropDownAndReverse(waves)
                // Have the invaders landed
                if (invader.position.bottom >= size.y && invader.isVisible) {
                    lost = true;
                }
            }
        }
    }

    private fun draw() {
        // Make sure our drawing surface is valid or the game will crash
        if (holder.surface.isValid) {
            // Lock the canvas ready to draw
            canvas = holder.lockCanvas()

            // Draw the background color
            canvas.drawColor(Color.argb(255, 0, 0, 0))

            // Choose the brush color for drawing
            paint.color = Color.argb(255, 0, 255, 0)

            // Draw all the game objects here
            // Now draw the player spaceship
            canvas.drawBitmap(playerShip.bitmap, playerShip.position.left, playerShip.position.top, paint)

            // Draw the invaders
            for (invader in invaders) {
                if (invader.isVisible) {
                    if (uhOrOh) {
                        canvas.drawBitmap(Invader.bitmap1,
                            invader.position.left,
                            invader.position.top,
                            paint)
                    } else {
                        canvas.drawBitmap(Invader.bitmap2,
                            invader.position.left,
                            invader.position.top,
                            paint)
                    }
                }
            }

            // Draw the bricks if visible
            for (brick in bricks) {
                if (brick.isVisible) {
                    canvas.drawRect(brick.position, paint)
                }
            }

            // Draw the players playerBullet if active
            if (playerBullet.isActive) {
                canvas.drawRect(playerBullet.position, paint)
            }

            // Draw the invaders bullets
            for (bullet in invadersBullets) {
                if (bullet.isActive) {
                    canvas.drawRect(bullet.position, paint)
                }
            }

            // Draw the score and remaining lives
            // Change the brush color
            paint.color = Color.argb(255, 255, 255, 255)
            paint.textSize = 40f
            canvas.drawText(
                "Score: $score   Lives: $lives Wave: " +
                    "$waves HI: $highScore", 20f, 75f, paint
            )

            // Draw everything to the screen
            holder.unlockCanvasAndPost(canvas)
        }
    }

    // If SpaceInvadersActivity is paused/stopped
    // then shut down our thread.
    fun onPause() {
        playing = false
        try {
            gameThread.join()
        } catch (e: InterruptedException) {
            Log.e("Error:", "joining thread")
        }
    }

    // If SpaceInvadersActivity is started then
    // start our thread.
    fun onResume() {
        playing = true
        prepareLevel()
        gameThread.start()
    }

    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {

        return true
    }
}
